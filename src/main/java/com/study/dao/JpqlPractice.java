package com.study.dao;

import java.io.Serializable;
import java.util.List;

public interface JpqlPractice<T> extends Serializable {
	
	//지네릭스를 사용함으로써, 유동적으로 타입을 정해 줄 수 있다.
	public List<T> getAll();
}
