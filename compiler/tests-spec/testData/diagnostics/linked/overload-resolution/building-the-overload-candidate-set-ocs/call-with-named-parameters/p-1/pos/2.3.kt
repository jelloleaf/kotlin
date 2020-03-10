// !LANGUAGE: +NewInference
// !DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 0.1-280
 * PLACE: overload-resolution, building-the-overload-candidate-set-ocs, call-with-named-parameters -> paragraph 1 -> sentence 2
 * RELEVANT PLACES: overload-resolution, building-the-overload-candidate-set-ocs, call-with-named-parameters -> paragraph 2 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: Implicit receiver: sets of explicitly imported, declared in the package scope and star-imported extension callables
 */

// FILE: Extensions.kt
package libPackageExplicit

public fun String.padEnd(lengthParam: Int, padChar: Char = ' '): String = TODO()

// FILE: Extensions.kt
package libPackage

public fun String.padEnd(lengthParam: Int, padChar: Char = ' '): String = TODO()

// FILE: TestCase1.kt
package tests

import libPackage.*
import libPackageExplicit.padEnd

// TESTCASE NUMBER: 1
fun case1(s: String) {
    <!DEBUG_INFO_AS_CALL("fqName: libPackageExplicit.padEnd; typeCall: extension function")!>s.padEnd(lengthParam = 5)<!>
}
