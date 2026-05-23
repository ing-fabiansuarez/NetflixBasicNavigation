package me.fabiansuarez.compose.lab.netflixbasicnavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
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
import me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.viewmodel.RegistrationViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NetflixBasicNavigationTheme {
                val navController = rememberNavController()
                
                // Shared ViewModel for the entire registration flow
                val registrationViewModel: RegistrationViewModel = viewModel()
                
                NavHost(navController = navController, startDestination = NetflixRoutes.LOGIN) {
                    composable(NetflixRoutes.LOGIN) {
                        LoginScreen(
                            onLoginClick = {
                                navController.navigate(NetflixRoutes.HOME) {
                                    popUpTo(NetflixRoutes.LOGIN) { inclusive = true }
                                }
                            },
                            onRegisterClick = {
                                navController.navigate(NetflixRoutes.REGISTER_STEP1)
                            }
                        )
                    }
                    composable(NetflixRoutes.REGISTER_STEP1) {
                        RegisterScreen(
                            viewModel = registrationViewModel,
                            onBackClick = {
                                navController.popBackStack()
                            },
                            onNextClick = {
                                navController.navigate(NetflixRoutes.REGISTER_STEP2)
                            },
                            onLoginClick = {
                                navController.navigate(NetflixRoutes.LOGIN) {
                                    popUpTo(NetflixRoutes.LOGIN) { inclusive = true }
                                }
                            }
                        )
                    }
                    composable(NetflixRoutes.REGISTER_STEP2) {
                        RegisterStep2Screen(
                            viewModel = registrationViewModel,
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
                            viewModel = registrationViewModel,
                            onBackClick = {
                                navController.popBackStack()
                            },
                            onNextClick = {
                                navController.navigate(NetflixRoutes.HOME) {
                                    popUpTo(NetflixRoutes.LOGIN) { inclusive = true }
                                }
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
}
