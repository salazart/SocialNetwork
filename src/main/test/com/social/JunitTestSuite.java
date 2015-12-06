package com.social;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//JUnit Suite Test
@RunWith(Suite.class)
@Suite.SuiteClasses({ 
	OkAccessTokenTest.class, VkAccessTokenTest.class, FbAccessTokenTest.class, AttachmentsTest.class
})
public class JunitTestSuite {

}
