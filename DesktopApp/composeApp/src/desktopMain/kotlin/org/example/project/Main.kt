package org.example.project
//user1@gmail.com
//{
//    "accessToken": "eyJpc3MiOiJBdXRoIFNlcnZlciIs",
//    "refreshToken": "eyJpc3MiOiJBdXRoIFKvvdte",
//    "message": " "
//}
import org.example.project.data.converter.eventConverter.HallItemConverter
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import org.example.project.data.converter.*
import org.example.project.data.converter.eventConverter.EventItemConverter
import org.example.project.data.converter.reportConverter.ReportItemConverter
import org.example.project.data.converter.eventConverter.EventPreviewItemConverter
import org.example.project.data.network.*
import org.example.project.data.preferences.TokenManager
import org.example.project.data.repository.*
import org.example.project.domain.entity.eventITems.HallItem
import org.example.project.domain.repository.*
import org.example.project.domain.usecase.*
import org.example.project.ui.AdminPanel
import org.example.project.ui.auth.LoginManager
import org.example.project.ui.auth.LoginScreen
import kotlin.system.exitProcess

@Composable
fun AppWindow() {
    val newHall: HallItem? = null
    var role by remember { mutableStateOf<String?>(null) }
    val resources = "E:\\AdminDesktop\\DesktopApp\\composeApp\\src\\desktopMain\\kotlin\\org\\example\\project\\assets"
    val networkModule = NetworkModule(resources)
    val tokenManager = TokenManager(resources + "/token.json")

    // Создание переменных для UseCase (начально они могут быть null)
    var employeeUseCase by remember { mutableStateOf<EmployeeUseCase?>(null) }
    var eventUseCase by remember { mutableStateOf<EventUseCase?>(null) }
    var incomeUseCase by remember { mutableStateOf<IncomeUseCase?>(null) }
    var reportUseCase by remember { mutableStateOf<ReportUseCase?>(null) }
    var loginUseCase by remember { mutableStateOf<LoginUseCase?>(null) }
    var loginManager by remember { mutableStateOf<LoginManager?>(null) }

    // Флаг, который будет указывать, что инициализация завершена
    var isInitialized by remember { mutableStateOf(false) }

    // Состояние для видимости панели
    var isPanelVisible by remember { mutableStateOf(true) }

    // Инициализация UseCase и ViewModel вручную
    LaunchedEffect(Unit) {
        // Инициализация Retrofit и API экземпляров
        val retrofit = networkModule.getInstance()

        // employee
        val employeeApi = retrofit.create(EmployeeApi::class.java)
        val employeeItemConverter = EmployeeItemConverter()
        val employeeRepository: EmployeeRepository = EmployeeRepositoryImpl(employeeApi, employeeItemConverter)
        employeeUseCase = EmployeeUseCase(employeeRepository)


        //refreshToken
        val refreshApi =retrofit.create(TokenRefreshApi::class.java)
        val loginConverter = LoginConverter()
        val refreshTokenRepository= RefreshTokenRepositoryImpl(refreshApi, loginConverter)
        val refreshTokenUseCase =RefreshTokenUseCase(refreshTokenRepository)

        // events
        val eventApi = retrofit.create(EventApi::class.java)
        val eventItemConverter = EventItemConverter()
        val eventPreviewItemConverter = EventPreviewItemConverter()
        val hallItemConverter = HallItemConverter()
        val eventRepository: EventRepository =
            EventRepositoryImpl(eventApi, eventItemConverter, eventPreviewItemConverter, hallItemConverter)
        eventUseCase = EventUseCase(eventRepository, refreshTokenUseCase, tokenManager)

        // report
        val reportApi = retrofit.create(ReportsApi::class.java)
        val reportItemConverter = ReportItemConverter()
        val reportRepository: ReportsRepository = ReportsRepositoryImpl(reportApi, reportItemConverter)
        reportUseCase = ReportUseCase(reportRepository)

        // Инициализация UseCase и ViewModel вручную
        val loginApi = retrofit.create(LoginApi::class.java)
        val loginResponseConverter = LoginResponseConverter()
        val loginRepository = LoginRepositoryImpl(loginApi, loginResponseConverter)
        loginUseCase = LoginUseCase(loginRepository)

        loginManager = LoginManager(loginUseCase!!, tokenManager)
        isInitialized = true
   }

    Window(
        onCloseRequest = { exitProcess(0) },
        state = WindowState(
            position = WindowPosition(Alignment.Center),
            size = DpSize(850.dp, 800.dp)
        ),
        title = "Management System",
        resizable = true,
    ) {
        if (!isInitialized) {
        } else if (role == null) {
            LoginScreen(
                onLoginSuccess = { newRole ->
                    role = newRole
                },
                loginManager = loginManager!!
            )

        } else {
            AdminPanel(
                onLogout = { role = null },
                isPanelVisible = isPanelVisible,
                employeeUseCase = employeeUseCase!!,
                eventUseCase = eventUseCase!!,
                reportUseCase = reportUseCase!!,
                onPanelHover = { isPanelVisible = true },
                newHall = newHall,
                onPanelToggle = { isPanelVisible = !isPanelVisible }
            )
        }
    }
}



fun main() = application {
    AppWindow()
}