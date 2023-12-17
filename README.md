# ChargeApp

> A car car charging and battery information application with charger locations.
> Implemented using Kotlin, Jetpack Compose in MVVM architecture.

## Please find the documentation regarding the project.
The Project is implemented using below tools and architecture.

## Additional requirements to run the project
You need to add 'GOOGLE_MAPS_API_KEY' in 'local.properties' file in order to display the map.

Tools
  1. Android Studio Hedgehog | 2023.1.1
  2. Android Gradle Plugin Version: 8.2.0
  3. Gradle Version: 8.2
  4. Used Version Catalog for handling library dependencies

Architecture
  1. MVVM
  2. Jetpack Compose
  3. Hilt for Dependancy Injection
  4. Kotlin Serialization for serialization
  5. BackdropScaffold and MotionLayout to handle scroll animations.

Please find the project folder structure.

📦chargerapp</br> ┣ 📂data</br> ┃ ┣ 📂di</br> ┃ ┃ ┣ 📜ChargingStationRepositoryModule.kt</br> ┃ ┃ ┗ 📜DatastoreModule.kt</br> ┃ ┣ 📂models</br> ┃ ┃ ┗ 📜ChargerLocations.kt</br> ┃ ┗ 📂repository</br> ┃ ┃ ┗ 📜ChargingStationRepository.kt</br> ┣ 📂navigation</br> ┃ ┣ 📜AppBottomNavigation.kt</br> ┃ ┣ 📜AppState.kt</br> ┃ ┣ 📜BottomNavItem.kt</br> ┃ ┣ 📜NavigationGraph.kt</br> ┃ ┗ 📜Route.kt</br> ┣ 📂ui</br> ┃ ┣ 📂activity</br> ┃ ┃ ┗ 📜MainActivity.kt</br> ┃ ┣ 📂customviews</br> ┃ ┃ ┣ 📜ChargingCircularProgress.kt</br> ┃ ┃ ┣ 📜ChargingInfoView.kt</br> ┃ ┃ ┣ 📜ChargingStationListItem.kt</br> ┃ ┃ ┣ 📜CircularDottedCanvas.kt</br> ┃ ┃ ┣ 📜CommonAppBar.kt</br> ┃ ┃ ┣ 📜DottedDecorationView.kt</br> ┃ ┃ ┣ 📜RevealButton.kt</br> ┃ ┃ ┗ 📜SearchView.kt</br> ┃ ┣ 📂motionscene</br> ┃ ┃ ┗ 📜DashboardMotionScene.kt</br> ┃ ┣ 📂screens</br> ┃ ┃ ┣ 📂dashboard</br> ┃ ┃ ┃ ┣ 📂backdrop</br> ┃ ┃ ┃ ┃ ┣ 📜BackLayer.kt</br> ┃ ┃ ┃ ┃ ┗ 📜FrontLayer.kt</br> ┃ ┃ ┃ ┣ 📂viewmodel</br> ┃ ┃ ┃ ┃ ┗ 📜DashboardScreenViewModel.kt</br> ┃ ┃ ┃ ┗ 📜DashboardScreen.kt</br> ┃ ┃ ┣ 📂mapscreen</br> ┃ ┃ ┃ ┗ 📜MapScreen.kt</br> ┃ ┃ ┣ 📂stationdetails</br> ┃ ┃ ┃ ┣ 📜ChargingStationDetails.kt</br> ┃ ┃ ┃ ┗ 📜StationDetailsViewModel.kt</br> ┃ ┃ ┣ 📜ProfileScreen.kt</br> ┃ ┃ ┗ 📜SearchScreen.kt</br> ┃ ┣ 📂shape</br> ┃ ┃ ┗ 📜NotchedRoundedCorners.kt</br> ┃ ┗ 📂theme</br> ┃ ┃ ┣ 📜Color.kt</br> ┃ ┃ ┣ 📜Dimen.kt</br> ┃ ┃ ┣ 📜Theme.kt</br> ┃ ┃ ┗ 📜Type.kt</br> ┣ 📂utility</br> ┃ ┣ 📜Extensions.kt</br> ┃ ┗ 📜Utiliy.kt</br> ┗ 📜ChargerApp.kt

 Please find the screenshots below

https://github.com/rishadappat/ChargerApp/assets/12482829/df034988-cac4-45b0-8d84-ac3e4e136197
  
 <table>
  <tr>
    <td><img src="screens/home.png" width=270 height=585></td>
    <td><img src="screens/home_collapsed.png" width=270 height=585></td>
  </tr>
   <tr>
    <td><img src="screens/search.png" width=270 height=585></td>
    <td><img src="screens/map.png" width=270 height=585></td>
  </tr>
    <tr>
    <td><img src="screens/station_details.png" width=270 height=585></td>
    <td><img src="screens/profile.png" width=270 height=585></td>
  </tr>
 </table>


 # The curve implementation.
 The curved view implementation was derived from the below medium post.
 https://proandroiddev.com/how-i-drew-custom-shapes-in-bottom-bar-c4539d86afd7
 It was implemented using drawing paths using Bézier curves. Additionally I have added the curves at the beginning and the end of the shape to create the Rounded-cornered curve-notched shape.

 # The dashboard scroll and expanding headerview.
 I was planning to implement 'Collapsible Toolbar Layout' which was available in the older xml based desing in Android. But, nothing similar was available in compose. Then I have remembered about Backdrop View which was available in Flutter. I was implemented one in my ownproject as well. You can find the source code in the blow repository.<br/>
 https://github.com/rishadappat/Bookledge<br/>
 It was available for jetpack compose as well. So I have decided to implement it using BackdropScaffold.<br/>
 # The animations
In Jetpack compose creating animations are a fun thing to do. And MotionLayout was an 'icing on a cake' feel. The MotionLayout is a combination of Constraints. When we add these constraints to the start and end positions, it will animate according to the progress value we are setting.
