package com.utils;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;

public class UpdateResults {

	private static final InfluxDB INFLXUDB = InfluxDBFactory.connect("http://localhost:8086", "root", "root");
	private static final String DB_NAME = "selenium_test_results";

	static {
		INFLXUDB.setDatabase(DB_NAME);
	}

	public static void post(final Point point) {
		INFLXUDB.write(point);
	}

}