// Generated from D:/coderepo/spring-cloud-demo/applications/test-learn/src/main/java/com/hzq/expression\MyExpr.g4 by ANTLR 4.12.0
package com.hzq.expression.myexpr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class MyExprParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, AND_=3, OR_=4, NOT_=5, COL_=6, HZQ_=7, STR_=8, SPECIAL_=9, 
		WS=10;
	public static final int
		RULE_stmt = 0, RULE_expr = 1;
	private static String[] makeRuleNames() {
		return new String[] {
			"stmt", "expr"
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

	@Override
	public String getGrammarFileName() { return "MyExpr.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MyExprParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StmtContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyExprListener ) ((MyExprListener)listener).enterStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyExprListener ) ((MyExprListener)listener).exitStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyExprVisitor ) return ((MyExprVisitor<? extends T>)visitor).visitStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(4);
			expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class OrExpressionContext extends ExprContext {
		public ExprContext left;
		public Token opt;
		public ExprContext right;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OR_() { return getToken(MyExprParser.OR_, 0); }
		public OrExpressionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyExprListener ) ((MyExprListener)listener).enterOrExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyExprListener ) ((MyExprListener)listener).exitOrExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyExprVisitor ) return ((MyExprVisitor<? extends T>)visitor).visitOrExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AndExpressionContext extends ExprContext {
		public ExprContext left;
		public Token opt;
		public ExprContext right;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode AND_() { return getToken(MyExprParser.AND_, 0); }
		public AndExpressionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyExprListener ) ((MyExprListener)listener).enterAndExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyExprListener ) ((MyExprListener)listener).exitAndExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyExprVisitor ) return ((MyExprVisitor<? extends T>)visitor).visitAndExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StrExpressionContext extends ExprContext {
		public TerminalNode STR_() { return getToken(MyExprParser.STR_, 0); }
		public StrExpressionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyExprListener ) ((MyExprListener)listener).enterStrExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyExprListener ) ((MyExprListener)listener).exitStrExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyExprVisitor ) return ((MyExprVisitor<? extends T>)visitor).visitStrExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NotExpressionContext extends ExprContext {
		public ExprContext left;
		public Token opt;
		public ExprContext right;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode NOT_() { return getToken(MyExprParser.NOT_, 0); }
		public NotExpressionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyExprListener ) ((MyExprListener)listener).enterNotExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyExprListener ) ((MyExprListener)listener).exitNotExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyExprVisitor ) return ((MyExprVisitor<? extends T>)visitor).visitNotExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DomainExpressionContext extends ExprContext {
		public Token field;
		public Token col;
		public Token left;
		public ExprContext exp;
		public Token right;
		public TerminalNode STR_() { return getToken(MyExprParser.STR_, 0); }
		public TerminalNode COL_() { return getToken(MyExprParser.COL_, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public DomainExpressionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyExprListener ) ((MyExprListener)listener).enterDomainExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyExprListener ) ((MyExprListener)listener).exitDomainExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyExprVisitor ) return ((MyExprVisitor<? extends T>)visitor).visitDomainExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SubExpressionContext extends ExprContext {
		public Token left;
		public ExprContext exp;
		public Token right;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public SubExpressionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyExprListener ) ((MyExprListener)listener).enterSubExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyExprListener ) ((MyExprListener)listener).exitSubExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyExprVisitor ) return ((MyExprVisitor<? extends T>)visitor).visitSubExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(18);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				_localctx = new SubExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(7);
				((SubExpressionContext)_localctx).left = match(T__0);
				setState(8);
				((SubExpressionContext)_localctx).exp = expr(0);
				setState(9);
				((SubExpressionContext)_localctx).right = match(T__1);
				}
				break;
			case 2:
				{
				_localctx = new DomainExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(11);
				((DomainExpressionContext)_localctx).field = match(STR_);
				setState(12);
				((DomainExpressionContext)_localctx).col = match(COL_);
				setState(13);
				((DomainExpressionContext)_localctx).left = match(T__0);
				setState(14);
				((DomainExpressionContext)_localctx).exp = expr(0);
				setState(15);
				((DomainExpressionContext)_localctx).right = match(T__1);
				}
				break;
			case 3:
				{
				_localctx = new StrExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(17);
				match(STR_);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(31);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(29);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
					case 1:
						{
						_localctx = new AndExpressionContext(new ExprContext(_parentctx, _parentState));
						((AndExpressionContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(20);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(21);
						((AndExpressionContext)_localctx).opt = match(AND_);
						setState(22);
						((AndExpressionContext)_localctx).right = expr(7);
						}
						break;
					case 2:
						{
						_localctx = new OrExpressionContext(new ExprContext(_parentctx, _parentState));
						((OrExpressionContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(23);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(24);
						((OrExpressionContext)_localctx).opt = match(OR_);
						setState(25);
						((OrExpressionContext)_localctx).right = expr(6);
						}
						break;
					case 3:
						{
						_localctx = new NotExpressionContext(new ExprContext(_parentctx, _parentState));
						((NotExpressionContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(26);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(27);
						((NotExpressionContext)_localctx).opt = match(NOT_);
						setState(28);
						((NotExpressionContext)_localctx).right = expr(5);
						}
						break;
					}
					} 
				}
				setState(33);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 6);
		case 1:
			return precpred(_ctx, 5);
		case 2:
			return precpred(_ctx, 4);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\n#\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0001"+
		"\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0003\u0001\u0013\b\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0005\u0001\u001e\b\u0001\n\u0001\f\u0001!\t\u0001\u0001\u0001"+
		"\u0000\u0001\u0002\u0002\u0000\u0002\u0000\u0000%\u0000\u0004\u0001\u0000"+
		"\u0000\u0000\u0002\u0012\u0001\u0000\u0000\u0000\u0004\u0005\u0003\u0002"+
		"\u0001\u0000\u0005\u0001\u0001\u0000\u0000\u0000\u0006\u0007\u0006\u0001"+
		"\uffff\uffff\u0000\u0007\b\u0005\u0001\u0000\u0000\b\t\u0003\u0002\u0001"+
		"\u0000\t\n\u0005\u0002\u0000\u0000\n\u0013\u0001\u0000\u0000\u0000\u000b"+
		"\f\u0005\b\u0000\u0000\f\r\u0005\u0006\u0000\u0000\r\u000e\u0005\u0001"+
		"\u0000\u0000\u000e\u000f\u0003\u0002\u0001\u0000\u000f\u0010\u0005\u0002"+
		"\u0000\u0000\u0010\u0013\u0001\u0000\u0000\u0000\u0011\u0013\u0005\b\u0000"+
		"\u0000\u0012\u0006\u0001\u0000\u0000\u0000\u0012\u000b\u0001\u0000\u0000"+
		"\u0000\u0012\u0011\u0001\u0000\u0000\u0000\u0013\u001f\u0001\u0000\u0000"+
		"\u0000\u0014\u0015\n\u0006\u0000\u0000\u0015\u0016\u0005\u0003\u0000\u0000"+
		"\u0016\u001e\u0003\u0002\u0001\u0007\u0017\u0018\n\u0005\u0000\u0000\u0018"+
		"\u0019\u0005\u0004\u0000\u0000\u0019\u001e\u0003\u0002\u0001\u0006\u001a"+
		"\u001b\n\u0004\u0000\u0000\u001b\u001c\u0005\u0005\u0000\u0000\u001c\u001e"+
		"\u0003\u0002\u0001\u0005\u001d\u0014\u0001\u0000\u0000\u0000\u001d\u0017"+
		"\u0001\u0000\u0000\u0000\u001d\u001a\u0001\u0000\u0000\u0000\u001e!\u0001"+
		"\u0000\u0000\u0000\u001f\u001d\u0001\u0000\u0000\u0000\u001f \u0001\u0000"+
		"\u0000\u0000 \u0003\u0001\u0000\u0000\u0000!\u001f\u0001\u0000\u0000\u0000"+
		"\u0003\u0012\u001d\u001f";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}