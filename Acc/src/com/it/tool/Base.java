package com.it.tool;

import com.google.gson.GsonBuilder;

public class Base {
	@Override
	public String toString() {
		// TODO �Զ����ɵķ������
		return new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd").setPrettyPrinting().create().toJson(this);
	}
}
