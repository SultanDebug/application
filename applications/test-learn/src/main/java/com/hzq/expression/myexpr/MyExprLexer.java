// Generated from D:/coderepo/spring-cloud-demo/applications/test-learn/src/main/java/com/hzq/expression\MyExpr.g4 by ANTLR 4.12.0
package com.hzq.expression.myexpr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class MyExprLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, AND_=3, OR_=4, NOT_=5, COL_=6, HZQ_=7, STR_=8, SPECIAL_=9, 
		WS=10;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "AND_", "OR_", "NOT_", "COL_", "HZQ_", "STR_", "SPECIAL_", 
			"A", "N", "D", "O", "R", "H", "Z", "Q", "T", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, "AND_", "OR_", "NOT_", "COL_", "HZQ_", "STR_", "SPECIAL_", 
			"WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public MyExprLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MyExpr.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\nZ\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001"+
		"\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0007\u0004\u0007?\b\u0007\u000b\u0007\f\u0007@\u0001\b\u0001\b\u0001"+
		"\t\u0001\t\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001"+
		"\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u0010"+
		"\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0000\u0000\u0013\u0001\u0001\u0003\u0002\u0005\u0003\u0007"+
		"\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\u0000\u0015\u0000"+
		"\u0017\u0000\u0019\u0000\u001b\u0000\u001d\u0000\u001f\u0000!\u0000#\u0000"+
		"%\n\u0001\u0000\r\u0002\u0000::==\u0004\u000009AZaz\u0080\u8000\ufffe"+
		"\u0007\u0000##%\',,./;;?@__\u0002\u0000AAaa\u0002\u0000NNnn\u0002\u0000"+
		"DDdd\u0002\u0000OOoo\u0002\u0000RRrr\u0002\u0000HHhh\u0002\u0000ZZzz\u0002"+
		"\u0000QQqq\u0002\u0000TTtt\u0003\u0000\t\n\r\r  Q\u0000\u0001\u0001\u0000"+
		"\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000"+
		"\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000"+
		"\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000"+
		"\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000"+
		"\u0000%\u0001\u0000\u0000\u0000\u0001\'\u0001\u0000\u0000\u0000\u0003"+
		")\u0001\u0000\u0000\u0000\u0005+\u0001\u0000\u0000\u0000\u0007/\u0001"+
		"\u0000\u0000\u0000\t2\u0001\u0000\u0000\u0000\u000b6\u0001\u0000\u0000"+
		"\u0000\r8\u0001\u0000\u0000\u0000\u000f>\u0001\u0000\u0000\u0000\u0011"+
		"B\u0001\u0000\u0000\u0000\u0013D\u0001\u0000\u0000\u0000\u0015F\u0001"+
		"\u0000\u0000\u0000\u0017H\u0001\u0000\u0000\u0000\u0019J\u0001\u0000\u0000"+
		"\u0000\u001bL\u0001\u0000\u0000\u0000\u001dN\u0001\u0000\u0000\u0000\u001f"+
		"P\u0001\u0000\u0000\u0000!R\u0001\u0000\u0000\u0000#T\u0001\u0000\u0000"+
		"\u0000%V\u0001\u0000\u0000\u0000\'(\u0005(\u0000\u0000(\u0002\u0001\u0000"+
		"\u0000\u0000)*\u0005)\u0000\u0000*\u0004\u0001\u0000\u0000\u0000+,\u0003"+
		"\u0013\t\u0000,-\u0003\u0015\n\u0000-.\u0003\u0017\u000b\u0000.\u0006"+
		"\u0001\u0000\u0000\u0000/0\u0003\u0019\f\u000001\u0003\u001b\r\u00001"+
		"\b\u0001\u0000\u0000\u000023\u0003\u0015\n\u000034\u0003\u0019\f\u0000"+
		"45\u0003#\u0011\u00005\n\u0001\u0000\u0000\u000067\u0007\u0000\u0000\u0000"+
		"7\f\u0001\u0000\u0000\u000089\u0003\u001d\u000e\u00009:\u0003\u001f\u000f"+
		"\u0000:;\u0003!\u0010\u0000;<\u0003\u000b\u0005\u0000<\u000e\u0001\u0000"+
		"\u0000\u0000=?\u0007\u0001\u0000\u0000>=\u0001\u0000\u0000\u0000?@\u0001"+
		"\u0000\u0000\u0000@>\u0001\u0000\u0000\u0000@A\u0001\u0000\u0000\u0000"+
		"A\u0010\u0001\u0000\u0000\u0000BC\u0007\u0002\u0000\u0000C\u0012\u0001"+
		"\u0000\u0000\u0000DE\u0007\u0003\u0000\u0000E\u0014\u0001\u0000\u0000"+
		"\u0000FG\u0007\u0004\u0000\u0000G\u0016\u0001\u0000\u0000\u0000HI\u0007"+
		"\u0005\u0000\u0000I\u0018\u0001\u0000\u0000\u0000JK\u0007\u0006\u0000"+
		"\u0000K\u001a\u0001\u0000\u0000\u0000LM\u0007\u0007\u0000\u0000M\u001c"+
		"\u0001\u0000\u0000\u0000NO\u0007\b\u0000\u0000O\u001e\u0001\u0000\u0000"+
		"\u0000PQ\u0007\t\u0000\u0000Q \u0001\u0000\u0000\u0000RS\u0007\n\u0000"+
		"\u0000S\"\u0001\u0000\u0000\u0000TU\u0007\u000b\u0000\u0000U$\u0001\u0000"+
		"\u0000\u0000VW\u0007\f\u0000\u0000WX\u0001\u0000\u0000\u0000XY\u0006\u0012"+
		"\u0000\u0000Y&\u0001\u0000\u0000\u0000\u0002\u0000@\u0001\u0006\u0000"+
		"\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}