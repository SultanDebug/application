package com.hzq.netty.leetcode;

import com.alibaba.fastjson.JSON;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

/**
 * @author Huangzq
 * @title: Twitter
 * @projectName applications
 * @date 2020/4/13 9:36
 */
public class Twitter {
    /**
     * 设计一个简化版的推特(Twitter)，可以让用户实现发送推文，关注/取消关注其他用户，能够看见关注人（包括自己）的最近十条推文。你的设计需要支持以下的几个功能：
     *
     * postTweet(userId, tweetId): 创建一条新的推文
     * getNewsFeed(userId): 检索最近的十条推文。每个推文都必须是由此用户关注的人或者是用户自己发出的。推文必须按照时间顺序由最近的开始排序。
     * follow(followerId, followeeId): 关注一个用户
     * unfollow(followerId, followeeId): 取消关注一个用户
     */

    Map<Integer,List<UserPost>> userPostMap = new ConcurrentSkipListMap<>();

    Map<Integer, Set<Integer>> userFollMap = new ConcurrentHashMap<>();

    int time = 0;

    /** Initialize your data structure here. */
    public Twitter() {

    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        List<UserPost> userPosts = userPostMap.get(userId);
        if(userPosts == null){
            userPosts = new ArrayList<>();
        }
        UserPost userPost = new UserPost();
        userPost.settId(tweetId);
        userPost.setTime(time++);
        userPost.setUserId(userId);
        userPosts.add(userPost);
        userPostMap.put(userId,userPosts);
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        Set<Integer> follows = userFollMap.get(userId);
        List<UserPost> userPosts = userPostMap.get(userId);

        List<UserPost> results = new ArrayList<>();
        if(userPosts != null){
            results.addAll(userPosts);
        }

        if(follows != null){
            for (Integer follow : follows) {
                List<UserPost> tmp = userPostMap.get(follow);
                if(tmp != null)results.addAll(tmp);
            }
        }

        results.sort((o1, o2) -> Long.compare(o2.time, o1.time));

        return results.stream().map(UserPost::gettId).limit(10).collect(Collectors.toList());
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if(followerId == followeeId){
            return;
        }
        Set<Integer> follows = userFollMap.get(followerId);
        if(follows == null){
            follows = new HashSet<>();
        }
        follows.add(followeeId);

        userFollMap.put(followerId,follows);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(followerId == followeeId){
            return;
        }
        Set<Integer> follows = userFollMap.get(followerId);
        if(follows == null){
            return;
        }
        follows.remove(followeeId);
    }

    public static void main(String[] args) {
        Twitter obj = new Twitter();
        obj.postTweet(1,5);
        obj.follow(1,1);
//        obj.postTweet(1,3);
//        List<Integer> param_3 = obj.getNewsFeed(1);
//        obj.follow(2,1);
//        List<Integer> param_4 = obj.getNewsFeed(2);
//        obj.unfollow(2,1);
        List<Integer> param_5 = obj.getNewsFeed(1);

        System.out.println(JSON.toJSONString(param_5));

//        obj.postTweet(2,5);
//        obj.follow(1,2);
//        List<Integer> param_2 = obj.getNewsFeed(1);
//        obj.follow(1,3);
//        obj.unfollow(1,2);
    }

    private static class UserPost implements Comparable<UserPost>{
        int userId;
        int tId;
        int time;


        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int gettId() {
            return tId;
        }

        public void settId(int tId) {
            this.tId = tId;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        @Override
        public int compareTo(UserPost o) {
            return Long.compare(this.time, o.time);
        }
    }
}
