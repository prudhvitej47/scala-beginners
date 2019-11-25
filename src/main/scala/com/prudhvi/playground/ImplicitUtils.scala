package com.prudhvi.playground

import java.sql.Timestamp

object ImplicitUtils {
	
	implicit def bigDecimalToJavaBigDecimal(b: math.BigDecimal): java.math.BigDecimal = b.underlying
	
	implicit class timestampExtensions(t1: Timestamp) {
		def beforeOrEqual(t2: Timestamp): Boolean = t1.before(t2) || t1.equals(t2)
	}
}
