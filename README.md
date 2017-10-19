# SO_Question_AndroidInjectionInTestsWithDagger
Project for presenting the complete solution with an Android problem, when trying to test components injected by Dagger 2.11 with AndroidInjector. 

How to inject a mock or fake for an injected dependency using Dagger 2.10/2.11 and its AndroidInjector?

I have an Android project using Dagger 2.11. And I want to test a feature on it.
There is a single Activity, which calls an Internet page and displays the result.
The network call is done via an injected NetworkApi, in this case a simple Volley request.
Dagger is setup via the new AndroidInjector Feature and I have a NetworkAPI module, an
module for the Activity and an AppModule for binding the Application as an Context.

My problem is, when I want to test that the activity displays the response from the network-api.
Therefore I tried to inject an Fake object (ImmediateResponseNetwork.class) in the test, to not rely
on the physical network. 

My Dagger injection setup is based on the Google Android Architecture Blueprint Todo-MVP-Dagger
https://github.com/googlesamples/android-architecture/tree/todo-mvp-dagger/

My current best approach to inject the Fake in the test is to override the NetworkAPIModule.
(Idea is from here: https://artemzin.com/blog/jfyi-overriding-module-classes-with-dagger2/
So I have an overriden every Module from the production code and the Application (TestMyApplication.class)

But I am somehow missing the point, where I tell Android, that it should use the TestMyApplication and
with it the NetworkApiModule, which provides the Fake networkApi for the test.

The specific question is, how do I inject/force the tests to use the Fake Network using Dagger?
But I am open to suggestions, how to make it easier than that, because on large projects, I would not like to
rewrite lots of modules in the tests.
