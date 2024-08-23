# Optional Class 
- The Optional class is commonly used in projects to handle situations where a value might be absent, making the code more robust and avoiding the pitfalls of null references.

# Methods 
- static <T> Optional<T> of(T value)
Creates an Optional with the specified non-null value.

- static <T> Optional<T> ofNullable(T value)
Creates an Optional with the specified value or an empty Optional if the value is null.

- static <T> Optional<T> empty()
Returns an empty Optional instance.

T get()
Returns the value if present; otherwise, throws NoSuchElementException.

boolean isPresent()
Returns true if a value is present, otherwise false.

boolean isEmpty() (Java 11+)
Returns true if the Optional is empty, otherwise false.

void ifPresent(Consumer<? super T> action)
Executes the given action if a value is present.

void ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction) (Java 9+)
Executes the given action if a value is present, otherwise executes the emptyAction.

<U> Optional<U> map(Function<? super T, ? extends U> mapper)
Applies the mapping function if a value is present and returns an Optional of the result.

<U> Optional<U> flatMap(Function<? super T, Optional<U>> mapper)
Applies the mapping function if a value is present and returns the Optional from the function.

Optional<T> filter(Predicate<? super T> predicate)
Returns an Optional containing the value if it matches the predicate, otherwise returns an empty Optional.

T orElse(T other)
Returns the value if present; otherwise, returns other.

T orElseGet(Supplier<? extends T> other)
Returns the value if present; otherwise, returns the result of the supplying function.

<X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X
Returns the value if present; otherwise, throws an exception created by the supplier.

T orElseThrow() (Java 10+)
Returns the value if present, otherwise throws NoSuchElementException.

Stream<T> stream() (Java 9+)
Returns a sequential Stream containing the value if present, otherwise an empty Stream.

Optional<T> or(Supplier<? extends Optional<? extends T>> supplier) (Java 9+)
Returns the current Optional if a value is present, otherwise returns an Optional produced by the supplier.
