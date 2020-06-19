/*
 * Copyright 2010-2018 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package kotlin.dom

import org.w3c.dom.*
import kotlin.internal.LowPriorityInOverloadResolution

/** Returns true if the element has the given CSS class style in its 'class' attribute */
@LowPriorityInOverloadResolution
@Deprecated(
    message = "This API is moved to another package, use 'kotlinx.dom.hasClass' instead.",
    replaceWith = ReplaceWith("this.hasClass(cssClass)", "kotlinx.dom.hasClass")
)
fun Element.hasClass(cssClass: String): Boolean = className.matches("""(^|.*\s+)$cssClass($|\s+.*)""".toRegex())

/**
 * Adds CSS class to element. Has no effect if all specified classes are already in class attribute of the element
 *
 * @return true if at least one class has been added
 */
@LowPriorityInOverloadResolution
@Deprecated(
    message = "This API is moved to another package, use 'kotlinx.dom.addClass' instead.",
    replaceWith = ReplaceWith("this.addClass(cssClasses)", "kotlinx.dom.addClass")
)
fun Element.addClass(vararg cssClasses: String): Boolean {
    val missingClasses = cssClasses.filterNot { hasClass(it) }
    if (missingClasses.isNotEmpty()) {
        val presentClasses = className.trim()
        className = buildString {
            append(presentClasses)
            if (!presentClasses.isEmpty()) {
                append(" ")
            }
            missingClasses.joinTo(this, " ")
        }
        return true
    }

    return false
}

/**
 * Removes all [cssClasses] from element. Has no effect if all specified classes are missing in class attribute of the element
 *
 * @return true if at least one class has been removed
 */
@LowPriorityInOverloadResolution
@Deprecated(
    message = "This API is moved to another package, use 'kotlinx.dom.removeClass' instead.",
    replaceWith = ReplaceWith("this.removeClass(cssClasses)", "kotlinx.dom.removeClass")
)
fun Element.removeClass(vararg cssClasses: String): Boolean {
    if (cssClasses.any { hasClass(it) }) {
        val toBeRemoved = cssClasses.toSet()
        className = className.trim().split("\\s+".toRegex()).filter { it !in toBeRemoved }.joinToString(" ")
        return true
    }

    return false
}
