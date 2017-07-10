# annotation-processor-sample

This is not a "Builder pattern" tutorial or something like that. It is about how we can make an annotation processor to avoid boilerplates and repeated codes.

## What does this apt do?

Well lets take a look at a very simple type of bean with getters and builder setters:

```java
public class TestClass {
    private long id;
    private String name;
    private int someField;
    private Activity activity;

    public long getId() {
        return id;
    }

    public TestClass setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TestClass setName(String name) {
        this.name = name;
        return this;
    }

    public int getSomeField() {
        return someField;
    }

    public TestClass setSomeField(int someField) {
        this.someField = someField;
        return this;
    }

    public Activity getActivity() {
        return activity;
    }

    public TestClass setActivity(Activity activity) {
        this.activity = activity;
        return this;
    }
}
```

And we instantiate this class like
```java
TestClass testClass = new TestClass()
    ...
    .setSomeField(...)
    .setName(...)
```

So we have four fields and implemented 4+4 getter and setter methods. Now, what if we had 15 fields? we would have to make 15 getters and 15 setters. That's a total of 30! Of course today its very easy to use IDEs to make that boilerplate for us. but this is just an example of how much we use boilerplate codes. there are a whole lot of other examples for this topic. For example take a look at [ButterKnife](https://github.com/JakeWharton/butterknife). This library took us out of the darkness. It made our world a better place.

with this apt(Annotation Processing Tool) all you have to do is to annotate your POJO with `@Builder` and make our fields *non private*

```java
@Builder
public class TestClass {
    long id;
    String name;
    int someField;
    Activity activity;
}
```

`@Builder` does no magic. it just creates all the boilerplates we need and puts them inside another java class called `%CLASS_NAME%Builder.java` under `app/build/generated/source/apt/debug/%PACKAGE_NAME%/` folder.

For example `TestClassBuilder.java` looks like this :

```java
// This file is auto-generated and should not be edited.
package ir.mirrajabi.aptsample;

import android.app.Activity;
import java.lang.String;

public class TestClassBuilder {
  private TestClass buildable;

  private TestClassBuilder() {
  }

  public TestClassBuilder someField(int someField) {
    buildable.someField = someField;
    return this;
  }

  public TestClassBuilder activity(Activity activity) {
    buildable.activity = activity;
    return this;
  }

  public TestClassBuilder id(long id) {
    buildable.id = id;
    return this;
  }

  public TestClassBuilder name(String name) {
    buildable.name = name;
    return this;
  }

  public static TestClassBuilder having() {
    return new TestClassBuilder();
  }

  TestClass get() {
    return this.buildable;
  }
}
```

And we make and instance of our `TestClass` like this:

```java
TestClass testClass = TestClassBuilder.having()
                .activity(this)
                .someField(5)
                .id(654)
                .name("Sample")
                .get();
```
But remember, we just annotated our class with `@Builder` and nothing else. the compiler did the rest for us.

I guess thats enough for a *readme* :))

## Usefull reads

- [ANNOTATION PROCESSING 101 - by Hannes Dorfmann](hannesdorfmann.com/annotation-processing/annotationprocessing101)
- [Android Annotation Processing Setup using Gradle](http://blog.jensdriller.com/android-annotation-processing-setup-using-gradle/)

## Used libraries

- [Square's Javapoet](https://github.com/square/javapoet)
- [Google's AutoService](https://github.com/google/auto/tree/master/service)
