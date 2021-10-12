<h1 align="center">Vort</h1>


<p align="center">
  <a href="https://github.com/gaugustini/vort/releases"><img alt="Release" src="https://img.shields.io/github/v/release/gaugustini/vort"/></a>
  <a href="https://opensource.org/licenses/MIT"><img alt="License" src="https://img.shields.io/github/license/gaugustini/vort"/></a>
  <a href="https://android-arsenal.com/api?level=23"><img alt="API" src="https://img.shields.io/badge/API-23%2B-brightgreen"/></a>
  <a href="https://github.com/gaugustini"><img alt="Profile" src="https://img.shields.io/badge/-gaugustini-blue?logo=github"/></a> 
</p>


<p align="center">
Vort is an armor set search for Monster Hunter games for Android, similar to Athena's Armor Set Search.
</p>


## Status
This project is still in development (non-functional):

- [ ] Basic UI
- [ ] Game data
- [ ] Logic


## Building
Clone this repository and open it in [Android Studio](https://developer.android.com/studio).


## Download
Go to the [Releases](https://github.com/gaugustini/vort/releases) to download the latest APK.


## Tech stack
* Kotlin: [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) + [Flow](https://kotlinlang.org/docs/flow.html)
* Minimum SDK: [level 23 - Android 6.0](https://developer.android.com/studio/releases/platforms#6.0)
* MVVM Architecture (Model-View-ViewModel)
* JetPack (Android Architecture Components)
    * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle)
    * [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
    * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
    * [Room](https://developer.android.com/topic/libraries/architecture/room)
    * [Data Binding](https://developer.android.com/topic/libraries/data-binding)
    * [Navigation](https://developer.android.com/guide/navigation)
    * [RecyclerView](https://developer.android.com/guide/topics/ui/layout/recyclerview)
* [Material](https://www.material.io/) - Components and guidelines for a better UX/UI.
* [Hilt](https://dagger.dev/hilt/) for dependency injection.


## License
```
MIT License

Copyright (c) 2021 Gustavo Augustini

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
