package com.cafebaby.cafewechat.utils;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @author jared
 * 
 * @Description:id生成工具
 * 
 * @date Nov 5, 2014 6:14:29 PM
 * 
 */
public class IdUtils {
	private static final int BASE = 100000;
	private static Random RANDOM = new Random(System.currentTimeMillis());
	private static AtomicInteger serial = new AtomicInteger(BASE);

	/**
	 * 根据当前时间生成随机id
	 * 
	 * @return long类型id
	 */
	public static Long make() {
		return timeToId(System.currentTimeMillis());
	}

	/**
	 * 通过时间生成随机id
	 * 
	 * @param timeInMillis
	 * @return
	 */
	public static Long timeToId(long timeInMillis) {
		if (serial.intValue() > 9900 * BASE) {
			serial.set(BASE);
		}
		return timeInMillis / 1000 * 10000000 + serial.getAndAdd(BASE) + RANDOM.nextInt(BASE);
	}

	public static void main(String[] args) {
		List<Long> list = new ArrayList<>();
		Set<Long> set = new HashSet<>();
		//System.out.println(System.currentTimeMillis());
		Long before  =System.currentTimeMillis();
		for(int i=0;i<100000000;i++){
			if(i==1000)System.gc();
			set.add(make());
		}
		Iterator<Long> iterator =  set.iterator();
		while (iterator.hasNext()){
			System.out.println(iterator.next());
		}
		Long after = System.currentTimeMillis();
		System.out.println(after-before);
	}
}
