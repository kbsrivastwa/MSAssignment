package com.ms.stocks;

import java.sql.Timestamp;

public class MSStockDetails {
	public MSStockDetails(String stock_symbol, String _type, double last_dividend, Object fixed_dividend, double par_value,
			Timestamp timeStamp, int _quantity, String buy_sell_indicator, double trade_price) {
		super();
		this.stock_symbol = stock_symbol;
		this._type = _type;
		this.last_dividend = last_dividend;
		this.fixed_dividend = fixed_dividend;
		this.par_value = par_value;
		this.timeStamp = timeStamp;
		this._quantity = _quantity;
		this.buy_sell_indicator = buy_sell_indicator;
		this.trade_price = trade_price;

	}
	String stock_symbol;
	public String getStock_symbol() {
		return stock_symbol;
	}
	public void setStock_symbol(String stock_symbol) {
		this.stock_symbol = stock_symbol;
	}
	public String get_type() {
		return _type;
	}
	public void set_type(String _type) {
		this._type = _type;
	}
	public double getLast_dividend() {
		return last_dividend;
	}
	public void setLast_dividend(double last_dividend) {
		this.last_dividend = last_dividend;
	}
	public Object getFixed_dividend() {
		return fixed_dividend;
	}
	public void setFixed_dividend(Object fixed_dividend) {
		this.fixed_dividend = fixed_dividend;
	}
	public double getPar_value() {
		return par_value;
	}
	public void setPar_value(double par_value) {
		this.par_value = par_value;
	}
	public Timestamp get_timeStamp() {
		return timeStamp;
	}
	public void set_timeStamp(Timestamp _timeStamp) {
		this.timeStamp = _timeStamp;
	}
	public int get_quantity() {
		return _quantity;
	}
	public void set_quantity(int _quantity) {
		this._quantity = _quantity;
	}
	public String getBuy_sell_indicator() {
		return buy_sell_indicator;
	}
	public void setBuy_sell_indicator(String buy_sell_indicator) {
		this.buy_sell_indicator = buy_sell_indicator;
	}
	public double getTrade_price() {
		return trade_price;
	}
	public void setTrade_price(double trade_price) {
		this.trade_price = trade_price;
	}
	String _type;
	double last_dividend;
	Object fixed_dividend;
	double par_value;
	Timestamp timeStamp;
	int _quantity;
	String buy_sell_indicator;
	double trade_price;

	

}
