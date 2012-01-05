package checkers.signature.quals;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

import checkers.quals.ImplicitFor;
import checkers.quals.SubtypeOf;
import checkers.quals.TypeQualifier;

/**
 * Represents a field descriptor (JVM signature type) as defined in Java Language Specification: http://java.sun.com/docs/books/jvms/second_edition/html/ClassFile.doc.html (sec. 4.3.2)
 * Example:
 *	package edu.cs.washington;
 *	public class BinaryName {
 *		private class Inner {}
 *	}
 * In this example field descriptor for class BinaryName: Ledu/cs/washington/BinaryName;
 * and field descriptor for class Inner: Ledu/cs/washington/BinaryName$Inner;
 * @author Kivanc Muslu
 */
@TypeQualifier
@SubtypeOf(UnannotatedString.class)
@ImplicitFor(stringPatterns="^\\[*([BCDFIJSZ]|L[A-Za-z_][A-Za-z_0-9]*(/[A-Za-z_][A-Za-z_0-9]*)*(\\$[A-Za-z_][A-Za-z_0-9]*)?;)$")
@Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
public @interface FieldDescriptor {}