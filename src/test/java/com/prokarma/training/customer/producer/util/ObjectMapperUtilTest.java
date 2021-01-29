
package com.prokarma.training.customer.producer.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.prokarma.training.customer.producer.test.helper.TestDataHelper;

@ExtendWith(MockitoExtension.class)
class ObjectMapperUtilTest {

	@Test
	void testJSONString() {
		String expectedString = "{\"customerNumber\":\"IN04852048\",\"firstName\":\"VijayKumar\",\"lastName\":\"Sharma1234\",\"birthdate\":\"20-08-1991\",\"country\":\"India\",\"countryCode\":\"IN\",\"mobileNumber\":\"9398130143\",\"email\":\"vijaysharma@gmail.com\",\"address\":{\"addressLine1\":\"Address Line1\",\"addressLine2\":\"Address Line2\",\"street\":\"Street Name\",\"postalCode\":\"27459\"},\"customerStatus\":\"Open\"}";

		String result = ObjectMapperUtil.returnJsonFromObject(TestDataHelper.getCustomerData());

		assertEquals(expectedString, result);
	}
}
