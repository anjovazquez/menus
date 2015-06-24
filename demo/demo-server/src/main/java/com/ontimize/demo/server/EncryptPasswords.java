package com.ontimize.demo.server;

import com.ontimize.db.DBUtils;
import com.ontimize.db.UserEntity;

public class EncryptPasswords {

	public static void main (String[] args)
	{
		DBUtils.main(args);
		UserEntity.main(args);
	}
}