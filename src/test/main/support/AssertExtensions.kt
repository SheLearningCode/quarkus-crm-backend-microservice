package main.support
import org.assertj.core.api.Assertions
import org.assertj.core.api.ObjectAssert
import java.time.Instant

class AssertExtensions {
    // GENERIC
    infix fun <T> T.assertIsEqualTo(expected: T) {
        Assertions.assertThat(this).isEqualTo(expected)
    }

    infix fun <T> T.assertIsNotEqualTo(expected: T) {
        Assertions.assertThat(this).isNotEqualTo(expected)
    }

    infix fun <T> T.assertIsSameAs(expected: T) {
        Assertions.assertThat(this).isSameAs(expected)
    }

    private infix fun <T> T.assertIsEmpty(expected: T) {
        Assertions.assertThat(this).assertIsEmpty(expected)
    }

    fun <T> T.assertIsNotNull() {
        Assertions.assertThat(this).isNotNull
    }

    fun <T> T.assertIsNull() {
        Assertions.assertThat(this).isNull()
    }

    infix fun <T> T.assertIsInstanceOf(type: Class<*>): ObjectAssert<T> {
        return Assertions.assertThat(this).isInstanceOf(type)
    }

    // COLLECTION
    infix fun <T> Collection<T>.assertContainsExactlyInAnyOrder(expected: Array<T>) {
        Assertions.assertThat(this).containsExactlyInAnyOrder(*expected)
    }

    fun <T> Collection<T>.assertIsEmpty() {
        Assertions.assertThat(this).isEmpty()
    }

    fun <T> Collection<T>.assertIsNotEmpty() {
        Assertions.assertThat(this).isNotEmpty
    }

    infix fun <T> Collection<T>.assertContainsExactly(expected: Array<T>) {
        Assertions.assertThat(this).containsExactly(*expected)
    }

    infix fun <T> Collection<T>.assertHasSize(expected: Int) {
        Assertions.assertThat(this).hasSize(expected)
    }

    infix fun <T> Iterable<T>.assertContains(expected: T) {
        Assertions.assertThat(this).contains(expected)
    }

    // STRING / CHAR-SEQUENCE
    infix fun CharSequence.assertContains(expected: String) {
        Assertions.assertThat(this).contains(expected)
    }

    fun CharSequence.assertContainsAll(vararg values: CharSequence) {
        Assertions.assertThat(this).contains(*values)
    }

    infix fun CharSequence.assertStartsWith(value: CharSequence) {
        Assertions.assertThat(this).startsWith(value)
    }

    infix fun CharSequence.assertEndsWith(value: CharSequence) {
        Assertions.assertThat(this).endsWith(value)
    }

    infix fun CharSequence.assertIsEqualToIgnoringWhitespace(expected: CharSequence) {
        Assertions.assertThat(this).isEqualToIgnoringWhitespace(expected)
    }

    // BOOLEAN
    fun Boolean.assertIsTrue() {
        Assertions.assertThat(this).isTrue
    }

    fun Boolean.assertIsFalse() {
        Assertions.assertThat(this).isFalse
    }

    // INSTANT
    infix fun Instant.assertIsAfter(other: Instant) {
        Assertions.assertThat(this).isAfter(other)
    }

    infix fun Instant.assertIsBefore(other: Instant) {
        Assertions.assertThat(this).isBefore(other)
    }


}