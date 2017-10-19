# SO_Question_AndroidInjectionInTestsWithDagger
How to inject a mock or fake for an injected dependency using Dagger 2.10/2.11 and its AndroidInjector?

Project for presenting the complete solution of a Android problem I had, when trying to replace components in integration tests injected by Dagger 2.11 with AndroidInjector. 

The problem got solved, without asking the question @StackOverflow. The code works as of October 2017. 
Same notes and errors, which occurred and were solved or workarounded in the process of implementing this solution.

I go from latest solved problem to first one
1. When right-clicking on the specific test: MainActivityTest.afterStartEmptyPageMessageisDisplayed() in the Android Studio IDE, I got:
Test running startedTest running failed: Unable to find instrumentation info for: ComponentInfo{android ... AndroidJUnitRunner}. This happens, when the in build.gradle the path to the testInstrumentationRunner is not set, or not correclty set. It also happens, when you change the testRunner and already have some Run configurations in your IDE, using older testInstrumentation settings from gradle. Changeing the testInstrumentationRunner in gradle, doesn't update the configurations. You have to delete them and recreate them. Another typo-like error was to use the @RunWith-Annotation above my own TestRunner class (MyTestRunner.java).

2. To tell Dagger, that it has to generate code in the test directories of your project you have to add the lines: 
..* androidTestAnnotationProcessor "com.google.dagger:dagger-compiler:2.11"
..* androidTestAnnotationProcessor "com.google.dagger:dagger-android-processor:2.11"

3. This solutions overrides the Dagger setup of the production code, but not any part of it:
Concrete:
..* There is TestMyApplication, which still extends from DaggerApplication
..* TestAppComponent extends AppComponent and contains the list of alternative Module classes, especially our TestNetworkApiModule for overriding the NetworkApi.
..* TestNetworkApiModule overrides the provideNetworkAPI method with a fake
..* AppModule is not extended, because we want to use the original context

4. Your test folder should contain an own AndroidManifest.xml, which states your TestApplication in this case TestMyApplication. Without it, your TestApplication won't be found and the system uses your normal application (MyApplication in this sample).

5. Generally Dagger 2 documentation clearly says. Dagger is not for smallTests meaning UnitTests. It is ok, for IntegrationTests, but as visible here, it is not without some extra setup and boilerplate code. See the following documentation: https://google.github.io/dagger/testing.html#separate-component-configurations 

6. It is very easy to use non-Android-SDK injected classes with Dagger in UnitTests. See also here: ( https://google.github.io/dagger/testing.html )

7. An alternative approach for replacing modules, is to use Androids Gradles flavour Build system. All Modules with alternatives, are reimplemented in every flavour. See Googles Architecture Blueprint TODO-MVP-Dagger project: https://github.com/googlesamples/android-architecture/tree/todo-mvp-dagger/ . Pro is, that it is much shorter to write. The Con is, that you have to switch between configurations/flavours, when running your integration tests, which cost additional compile time too. You can shrink this problem (because those are mainly medium and large Tests (integration and end2end-tests), you won't run them often) and can let them run automatically on your build server, independent of your current TDD cycle/Flow etc.

8. Another alternative approach seems to be, to use Robolectric, which is currently on the way to become internally supported by Googles Android Team and Android Studio from Version 3.0+ . I will give it a try, because it will also offer the opportunity to run a lot more integration tests and unit tests with classes with Android Framework dependencies (like Parcelable) to be run on the JVM, allowing TDD to become really possible (fast enough). 
