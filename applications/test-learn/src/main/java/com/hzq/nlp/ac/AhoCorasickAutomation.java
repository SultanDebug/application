package com.hzq.nlp.ac;

import java.util.*;


/**
 * AC自动机，用于query和文档的匹配，使用的是Map存储节点构造的trie树而非hanlp的双数组trie,空间换时间的方式减少耗时,hanlp双数组trie为了减少空间,构建和查询的耗时会比当前的久
 *
 */
public class AhoCorasickAutomation {
    /**
     * 是否建立了failure表
     */
    private Boolean failureStatesConstructed = false;
    /**
     * 根结点
     */
    private Node root;


    public AhoCorasickAutomation() {
        this.root = new Node(true);
    }

    private static class Node {
        //用于放这个Node的所有子节点，储存形式是：Map(char, Node)
        private Map<Character, Node> map;
        //该节点处包含的所有pattern string
        private List<String> pattenStrings;
        //fail指针指向的node
        private Node failure;
        //是否为根结点
        private Boolean isRoot = false;


        public Node() {
            map = new HashMap<>();
            pattenStrings = new ArrayList<>();
        }


        public Node(Boolean isRoot) {
            this();
            this.isRoot = isRoot;
        }


        /**
         * 用于build trie，如果一个字符character存在于子节点中，不做任何操作，返回这个节点的node
         * 否则，建一个node，并将map(char,node)添加到当前节点的子节点里，并返回这个node
         *
         * @param character
         * @return
         */
        public Node insert(Character character) {
            Node node = this.map.get(character);
            if (node == null) {
                node = new Node();
                map.put(character, node);
            }
            return node;
        }


        public void addPattenString(String keyword) {
            pattenStrings.add(keyword);
        }


        public void addPattenString(Collection<String> keywords) {
            pattenStrings.addAll(keywords);
        }


        public Node find(Character character) {
            return map.get(character);
        }


        /**
         * 利用父节点fail node来寻找子节点的fail node
         * or
         * parseText时找下一个匹配的node
         */
        private Node nextState(Character transition) {
            //用于构建fail node时，这里的this是父节点的fail node
            //首先从父节点的fail node的子节点里找有没有值和当前失败节点的char值相同的
            Node state = this.find(transition);

            //如果找到了这样的节点，那么该节点就是当前失败位置节点的fail node
            if (state != null) {
                return state;
            }

            //如果没有找到这样的节点，而父节点的fail node又是root，那么返回root作为当前失败位置节点的fail node
            if (this.isRoot) {
                return this;
            }

            //如果上述两种情况都不满足，那么就对父节点的fail node的fail node再重复上述过程，直到找到为止
            //这个地方借鉴了KMP算法里面求解next列表的思想
            return this.failure.nextState(transition);
        }


        public Collection<Node> children() {
            return this.map.values();
        }


        public void setFailure(Node node) {
            failure = node;
        }


        public Node getFailure() {
            return failure;
        }


        //返回一个Node的所有子节点的键值，也就是这个子节点上储存的char
        public Set<Character> getTransitions() {
            return map.keySet();
        }


        public Collection<String> pattenString() {
            return this.pattenStrings == null ? Collections.emptyList() : this.pattenStrings;
        }
    }


    /**
     * 添加一个模式串(内部使用字典树构建)
     */
    public void addKeyword(String keyword) {
        if (keyword == null || keyword.length() == 0) {
            return;
        }
        Node currentState = this.root;
        for (Character character : keyword.toCharArray()) {
            //如果char已经在子节点里，返回这个节点的node；否则建一个node，并将map(char,node)加到子节点里去
            currentState = currentState.insert(character);
        }
        //在每一个尾节点处，将从root到尾节点的整个string添加到这个叶节点的PattenString里
        currentState.addPattenString(keyword);
    }

    /**
     * 用ac自动机做匹配，返回text里包含的pattern及其在text里的起始位置
     */
    public Collection<MatchString> parseText(String text) {
        //首先构建 fail表，如已构建则跳过
        checkForConstructedFailureStates();

        Node currentState = this.root;
        List<MatchString> collectedPattenStrings = new ArrayList<>();
        for (int position = 0; position < text.length(); position++) {
            Character character = text.charAt(position);
            //依次从子节点里找char，如果子节点没找到，就到子节点的fail node找，并返回最后找到的node；如果找不到就会返回root
            //这一步同时也在更新currentState，如果找到了就更新currentState为找到的node，没找到currentState就更新为root，相当于又从头开始找
            currentState = currentState.nextState(character);
            Collection<String> pattenStrings = currentState.pattenString();
            if (pattenStrings == null || pattenStrings.isEmpty()) {
                continue;
            }
            //如果找到的node的PattenString非空，说明有pattern被匹配到了
            for (String pattenString : pattenStrings) {
                collectedPattenStrings.add(new MatchString(position - pattenString.length() + 1, position, pattenString));
            }
        }
        //返回匹配到的所有patternp
        return collectedPattenStrings;
    }


    /**
     * 建立Fail表,使用BFS遍历
     */
    private void constructFailureStates() {
        Queue<Node> queue = new LinkedList<>();
        //首先从把root的子节点的fail node全设为root
        //然后将root的所有子节点加到queue里面
        for (Node depthOneState : this.root.children()) {
            depthOneState.setFailure(this.root);
            queue.add(depthOneState);
        }
        this.failureStatesConstructed = true;

        while (!queue.isEmpty()) {
            Node parentNode = queue.poll();
            //下面给parentNode的所有子节点找fail node
            //transition是父节点的子节点的char
            for (Character transition : parentNode.getTransitions()) {
                //childNode是子节点中对应上面char值的节点的Node值
                Node childNode = parentNode.find(transition);
                //将这个parentNode的所有子节点加入queue，在parentNode的所有兄弟节点都过了一遍之后，就会过这些再下一层的节点
                queue.add(childNode);
                //利用父节点的fail node来构建子节点的fail node
                Node failNode = parentNode.getFailure().nextState(transition);
                childNode.setFailure(failNode);
                //每个节点处的PattenString要加上它的fail node处的PattenString
                //因为能匹配到这个位置的话，那么fail node处的PattenString一定是匹配的pattern
                childNode.addPattenString(failNode.pattenString());
            }
        }
    }


    /**
     * 检查是否建立了Fail表(若没建立，则建立)
     */
    public void checkForConstructedFailureStates() {
        if (!this.failureStatesConstructed) {
            constructFailureStates();
        }
    }


    /**
     * 模式串对象
     */
    private static class MatchString {
        private final String keyword;   //匹配到的模式串
        private final int start;        //起点
        private final int end;          //终点

        public MatchString(final int start, final int end, final String keyword) {
            this.start = start;
            this.end = end;
            this.keyword = keyword;
        }

        public String getKeyword() {
            return this.keyword;
        }

        @Override
        public String toString() {
            return super.toString() + "=" + this.keyword;
        }
    }


    public static void main(String[] args) {
        AhoCorasickAutomation trie = new AhoCorasickAutomation();
        trie.addKeyword("he");
        trie.addKeyword("she");
        trie.addKeyword("his");
        trie.addKeyword("hers");

        //匹配text，并返回匹配到的pattern
        Collection<MatchString> pattenStrings = trie.parseText("ushers");
        for (MatchString matchString : pattenStrings) {
            System.out.println(matchString.start + " " + matchString.end + "\t" + matchString.getKeyword());
        }
    }
}
