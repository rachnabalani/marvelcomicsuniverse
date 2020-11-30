# marvel Comics Universe - Characters
I created this app to practice building my own app using all the latest trends and technologies that have been coming up in Android. Specially these ones - 

  > ~ MVVM architecture best practices
  
  > ~ RXJava
  
  > ~ LiveData (https://developer.android.com/topic/libraries/architecture/livedata)
  
  > ~ Android Jetpack -- ViewBinding, Navigation Component, DataBinding (https://developer.android.com/jetpack)
  
  > ~ Retrofit (https://www.raywenderlich.com/4539-android-networking) and (https://www.vogella.com/tutorials/Retrofit/article.html)
  
  > ~ Dagger2  (https://developer.android.com/training/dependency-injection/dagger-basics)
  
  > ~ Glide (https://www.raywenderlich.com/2945946-glide-tutorial-for-android-getting-started)

Here's a special highlight of other things I learnt while implementing this app and sources for future read - 

1. <b>Learned how to apply Kotlin extension functions.</b> This is being used on the ImageView library by android's widget package. I used an ext4ension function called loadImage and made it load the uri using Glide library. Also used Glide to auto-implement a progressBar while the image is extracted in the background.  Here's my reference to how I learnt it, and advanced reading - 
https://www.raywenderlich.com/10986797-extension-functions-and-properties-in-kotlin 

2. <b>Learnt the best design patterns to use with current Android improvements.</b> Some of the old architecture like MVC might be too heavy, compared to MVVM that helps us implement the Jetpack libraries better. Resource for easy revision on classic design pattern fundamentals - https://blog.mindorks.com/mastering-design-patterns-in-android-with-kotlin 

3. <b>Instead of using Intents to passs data/parcel around fragments, used Navigation Component's actions </b> Here's also a good article to summarise why using Navigation Component is the best idea for any newee app - https://medium.com/disney-streaming/why-i-happily-use-the-architecture-navigation-component-887277fce249 


And here's some screenshots on how it looks (didn't focus too much on making it fancy, this is just too learn new architectural concepts)
