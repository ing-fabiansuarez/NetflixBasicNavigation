package me.fabiansuarez.compose.lab.netflixbasicnavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.navigation.NetflixRoutes
import me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.screen.HomeScreen
import me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.screen.LoginScreen
import me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.screen.RegisterScreen
import me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.screen.RegisterStep2Screen
import me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.screen.RegisterStep3Screen
import me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.theme.NetflixBasicNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = NetflixRoutes.LOGIN) {
                composable(NetflixRoutes.LOGIN) {
                    LoginScreen(
                        onSuscribeClick = {
                            navController.navigate(NetflixRoutes.REGISTER_STEP1)
                        }
                    )
                }
                composable(NetflixRoutes.REGISTER_STEP1) {
                    RegisterScreen(
                        onBackClick = {
                            navController.popBackStack()
                        },
                        onRegisterStep2 = {
                            navController.navigate(NetflixRoutes.REGISTER_STEP2)
                        }
                    )
                }
                composable(NetflixRoutes.REGISTER_STEP2) {
                    RegisterStep2Screen(
                        onBackClick = {
                            navController.popBackStack()
                        },
                        onNextClick = {
                            navController.navigate(NetflixRoutes.REGISTER_STEP3)
                        }
                    )
                }
                composable(NetflixRoutes.REGISTER_STEP3) {
                    RegisterStep3Screen(
                        onBackClick = {
                            navController.popBackStack()
                        },
                        onNextClick = {
                            navController.navigate(NetflixRoutes.HOME)
                        }
                    )
                }
                composable(NetflixRoutes.HOME) {
                    HomeScreen()
                }
            }
        }
    }
}