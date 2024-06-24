package org.maduralang.lexer

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class LexerTest {

    private val lexer = Lexer()

    @Test
    fun `lexer should return an empty list on empty text input`() {
        val result = lexer.scan("")
        assertEquals(emptyList(), result)
    }

    @Test
    fun `lexer should recognize whitespaces`() {
        val space = " "
        val result = lexer.scan(space, ignoreWhitespaces = false)
        assertEquals(listOf(WhitespaceToken(space)), result)
    }

    @Test
    fun `lexer should recognize line breaks`() {
        val lineBreak = "\n"
        val result = lexer.scan(lineBreak, ignoreWhitespaces = false)
        assertEquals(listOf(WhitespaceToken(lineBreak)), result)
    }

    @Test
    fun `lexer should recognize single letter names`() {
        val shortName = "x"
        val result = lexer.scan(shortName)
        assertEquals(listOf(NameToken(shortName)), result)
    }

    @Test
    fun `lexer should recognize multi letter names`() {
        val longName = "length"
        val result = lexer.scan(longName)
        assertEquals(listOf(NameToken(longName)), result)
    }

    @Test
    fun `lexer should recognize keywords`() {
        val keyword = "if"
        val result = lexer.scan(keyword)
        assertEquals(listOf(KeywordToken(keyword)), result)
    }

    @Test
    fun `lexer should recognize single digit integers`() {
        val smallInteger = "3"
        val result = lexer.scan(smallInteger)
        assertEquals(listOf(NumberToken(smallInteger)), result)
    }

    @Test
    fun `lexer should recognize multi digit integers`() {
        val leet = "1337"
        val result = lexer.scan(leet)
        assertEquals(listOf(NumberToken(leet)), result)
    }

    @Test
    fun `lexer should allow separators in multi digit integers`() {
        val million = "1_000_000"
        val result = lexer.scan(million)
        assertEquals(listOf(NumberToken(million)), result)
    }

    @Test
    fun `lexer should recognize decimal numbers`() {
        val pi = "3.14"
        val result = lexer.scan(pi)
        assertEquals(listOf(NumberToken(pi)), result)
    }

    @Test
    fun `lexer should recognize decimal numbers starting with dot`() {
        val pi = ".50"
        val result = lexer.scan(pi)
        assertEquals(listOf(NumberToken(pi)), result)
    }

    @Test
    fun `lexer should allow separators in decimal numbers`() {
        val separatedFloat = "6_000.7_7"
        val result = lexer.scan(separatedFloat)
        assertEquals(listOf(NumberToken(separatedFloat)), result)
    }

    @Test
    fun `lexer should recognize hex numbers`() {
        val hexNumber = "0x7f"
        val result = lexer.scan(hexNumber)
        assertEquals(listOf(NumberToken(hexNumber)), result)
    }

    @Test
    fun `lexer should recognize bin numbers`() {
        val binNumber = "0b101"
        val result = lexer.scan(binNumber)
        assertEquals(listOf(NumberToken(binNumber)), result)
    }

    @Test
    fun `lexer should recognize attributes on numbers`() {
        val attribute = "50.successor"
        val result = lexer.scan(attribute)
        assertEquals(listOf(NumberToken("50"), SymbolToken("."), NameToken("successor")), result)
    }

    @Test
    fun `lexer should recognize strings`() {
        val greeting = "\"Hello\""
        val result = lexer.scan(greeting)
        assertEquals(listOf(StringToken(greeting)), result)
    }

    @Test
    fun `lexer should handle strings with escaped quotes correctly`() {
        val greeting = "\"He said: \\\"Hello\\\"\""
        val result = lexer.scan(greeting)
        assertEquals(listOf(StringToken(greeting)), result)
    }

    @Test
    fun `lexer should recognize quotes within other quotes`() {
        val quoteChar = "'\"'"
        val result = lexer.scan(quoteChar)
        assertEquals(listOf(StringToken(quoteChar)), result)
    }

    @Test
    fun `lexer should handle undelimited strings correctly`() {
        val undelimited = "\"still typing ..."
        val result = lexer.scan(undelimited)
        assertEquals(listOf(StringToken(undelimited)), result)
    }

    @Test
    fun `lexer should recognize simple symbols`() {
        val plus = "+"
        val result = lexer.scan(plus)
        assertEquals(listOf(SymbolToken(plus)), result)
    }

    @Test
    fun `lexer should recognize composite symbols`() {
        val fatArrow = "=>"
        val result = lexer.scan(fatArrow)
        assertEquals(listOf(SymbolToken(fatArrow)), result)
    }

    @Test
    fun `lexer should recognize various successive symbols `() {
        val access = "[]"
        val result = lexer.scan(access)
        assertEquals(listOf(SymbolToken("["), SymbolToken("]")), result)
    }

    @Test
    fun `lexer should recognize annotations`() {
        val annotation = "@Data"
        val result = lexer.scan(annotation)
        assertEquals(listOf(MetaToken(annotation)), result)
    }

    @Test
    fun `lexer should scan simple programs correctly`() {
        val programm = "fn main() => println(\"Hello world\")"
        val result = lexer.scan(programm)
        assertEquals(
            listOf(
                KeywordToken("fn"),
                NameToken("main"),
                SymbolToken("("),
                SymbolToken(")"),
                SymbolToken("=>"),
                NameToken("println"),
                SymbolToken("("),
                StringToken("\"Hello world\""),
                SymbolToken(")")
            ), result
        )
    }

    @Test
    fun `lexer should recognize comments`() {
        val comment = "const a // TODO"
        val result = lexer.scan(comment)
        assertEquals(listOf(KeywordToken("const"), NameToken("a"), CommentToken("// TODO")), result)
    }
}