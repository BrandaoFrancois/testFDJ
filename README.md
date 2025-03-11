# test FDJ

Little application developed for a job interview.

The subject were: 
  - The user can search for a soccer league (with auto-completion)
  - The user can select a soccer league to view the teams (filtering half of them) participating, sorted in reverse alphabetical order. 

<h3>
<img alt="List league screen" src="https://github.com/BrandaoFrancois/testFDJ/blob/main/README_ASSETS/screen1.png?raw=true" width="150" />
<img alt="List teams on league screen" src="https://github.com/BrandaoFrancois/testFDJ/blob/main/README_ASSETS/screen2.png?raw=true" width="150" />
<img alt="List teams on league screen horizontally" src="https://github.com/BrandaoFrancois/testFDJ/blob/main/README_ASSETS/screen3.png?raw=true" width="150" />
</h3>

## Technical stack

  - Android Kotlin Application / MVVM
  - Compose (Material 3)
  - Hilt
  - Coroutines / Coroutines Tests
  - Retrofit2 / Kotlin Serializer (Gson in first versions)
  - COIL

## Quality

[KTLint](https://github.com/pinterest/ktlint) approved !

## Next upgrades possibilities

  - Add a better error management for the User (Ex. Error message on UI or Toast)
  - Add the detection of bandwidth (Ex. Message with icon if not connected)
  - Adding new Tests (Integration tests, UI Tests)
