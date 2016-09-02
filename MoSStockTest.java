package com.ms.stocks;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
/**
 * @author Krishnaveni
 *
 */
public final class MoSStockTest {

	final static Logger LOGGER = Logger.getLogger(MoSStockTest.class);

	public final static void main(String[] args) {
		try {
			List<MSStockDetails> mSStockDetails = new ArrayList<MSStockDetails>();
			final Timestamp timestam = new Timestamp(Calendar.getInstance().getTime().getTime());
			mSStockDetails.add(new MSStockDetails("TEA", "common", 0, null, 100, timestam, 500, "BUY", 1000));
			mSStockDetails.add(new MSStockDetails("POP", "common", 8, null, 100, timestam, 600, "SELL", 2000));
			mSStockDetails.add(new MSStockDetails("ALE", "common", 23, null, 60, timestam, 700, "SELL", 3000));
			mSStockDetails.add(new MSStockDetails("GIN", "preferred", 8, 2, 100, timestam, 800, "BUY", 4000));
			mSStockDetails.add(new MSStockDetails("JOE", "common", 13, null, 250, timestam, 900, "SELL", 5000));
			for (int index = 0; index < mSStockDetails.size() && !mSStockDetails.isEmpty(); index++) {
				double dividendYield = 0;
				double price = 2500;
				double peRatio = 0;
				if (mSStockDetails.get(index).get_type().equals("common")) {
					final double lastDividend = mSStockDetails.get(index).getLast_dividend();
					dividendYield = (Double) calculateDividendYieldcommon(lastDividend, price);
					if (LOGGER.isInfoEnabled()) {
						LOGGER.info("For stock symbol ' " + mSStockDetails.get(index).getStock_symbol() + "'");
					}

				} else if (mSStockDetails.get(index).get_type().equals("preferred")) {
					double fixedDividend = (Integer) mSStockDetails.get(index).getFixed_dividend();
					final double parVaue = mSStockDetails.get(index).getPar_value();
					dividendYield = calculateDividendYieldpreferred(fixedDividend, price, parVaue);
					if (LOGGER.isInfoEnabled()) {
						LOGGER.info("For stock symbol'  " + mSStockDetails.get(index).getStock_symbol() + "'");
					}
				}
				peRatio = calculatePERatio(dividendYield, price);
				if (LOGGER.isInfoEnabled()) {
					LOGGER.info("Given any price : " + price);
					LOGGER.info("Calculated dividend Yield : " + dividendYield);
					LOGGER.info("Calculated peRatio : " + peRatio);
					LOGGER.info(
							"Recorded + Given trade details : Stock symbol : " + mSStockDetails.get(index).stock_symbol
									+ " , Quantity : " + mSStockDetails.get(index)._quantity + " , Buy or Sell : "
									+ mSStockDetails.get(index).buy_sell_indicator + " , Traded price : "
									+ mSStockDetails.get(index).trade_price + " , Timestamp : "
									+ mSStockDetails.get(index).timeStamp);
					LOGGER.info("\n");
				}
			}

			final double vwsp = calVolumeWeightedStkPrice(mSStockDetails);
			final double geometricMean = calgeometricmean(mSStockDetails);
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("Calculated Volume Weighted Stock Price : " + vwsp);
				LOGGER.info("Calculated Geometric Mean : " + geometricMean);
			}

		} catch (Exception e) {
			LOGGER.error(e);
		}
	}

	private static double calgeometricmean(List<MSStockDetails> mSStockDetails) {
		double productOfAllPrices = 1;
		double geometricMean = 0;
		int totalProducts = 0;
		int size = mSStockDetails.size();
		final boolean isEmpty = mSStockDetails.isEmpty();
		for (int index = 0, counter = 0; index < size && !isEmpty; index++) {

			productOfAllPrices *= mSStockDetails.get(index).getTrade_price();
			counter++;
			totalProducts = counter;
		}
		geometricMean = productOfAllPrices / totalProducts;
		return geometricMean;
	}

	private static double calVolumeWeightedStkPrice(List<MSStockDetails> mSStockDetails) {
		List<MSStockDetails> mSStockDetail = mSStockDetails;
		Double volumeWeigthedStockPrice = 0.0;
		Date currentTime = new Date();
		final Date startTime = new Date(currentTime.getTime() - (15 * 60 * 1000));
		double sumPrceQuan = 0.0;
		double sumOfQuanlity = 0.0;
		for (int index = 0; index < mSStockDetails.size() && !mSStockDetails.isEmpty(); index++) {
			Timestamp timeStamp = mSStockDetails.get(index).timeStamp;
			if (timeStamp.after(startTime) && timeStamp.before(currentTime)) {
				sumPrceQuan += mSStockDetail.get(index).get_quantity() * mSStockDetail.get(index).getTrade_price();
				sumOfQuanlity += mSStockDetail.get(index).get_quantity();
			}
		}
		volumeWeigthedStockPrice = sumPrceQuan / sumOfQuanlity;
		return volumeWeigthedStockPrice;
	}

	private static double calculatePERatio(double dividendYield, double price) {
		// double peRatio =
		return price / dividendYield;
	}

	private static Double calculateDividendYieldpreferred(double fixedDividend, double price, double parVaue) {
		int percentage = 100;
		double dividendYield = fixedDividend / percentage * parVaue / price;
		return dividendYield;

	}

	private static double calculateDividendYieldcommon(double lastDividend, double price) {

		double dividendYield = lastDividend / price;
		return dividendYield;
	}

}
