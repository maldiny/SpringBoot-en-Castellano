package com.maldiny.spring.boot.springbatch.chunk;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class CustomProcesor implements ItemProcessor<MyObject, MyObject> {

	@Override
	public MyObject process(MyObject arg0) throws Exception {
		
		arg0.setId(arg0.getId().toUpperCase());
		
		arg0.setName(arg0.getName().toUpperCase());
		
		return arg0;
	}

}