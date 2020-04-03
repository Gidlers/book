package com.it.tool;

import com.google.gson.GsonBuilder;

public class Base {
	@Override
	public String toString() {
		// TODO 自动生成的方法存根
		return new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd").setPrettyPrinting().create().toJson(this);
	}
}
