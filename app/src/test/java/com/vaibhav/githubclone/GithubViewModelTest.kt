package com.vaibhav.githubclone

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vaibhav.githubclone.model.Contributor
import com.vaibhav.githubclone.model.Profile
import com.vaibhav.githubclone.model.Repository
import com.vaibhav.githubclone.retrofitAPI.GithubRepository
import com.vaibhav.githubclone.retrofitAPI.GithubRepositoryImpl
import com.vaibhav.githubclone.retrofitAPI.RetrofitService
import com.vaibhav.githubclone.viewmodel.GithubViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response
import org.mockito.Mockito.verify
import org.junit.Assert.assertEquals

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class GithubViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    lateinit var profileObserver: Observer<Profile>

    @Mock
    private lateinit var repository: FakeGithubRepositoryImpl
    private lateinit var githubViewModel: GithubViewModel
    lateinit var profileResponse: Response<Profile>
    lateinit var profile: Profile
    lateinit var user: String


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        githubViewModel = GithubViewModel(repository)
        user = "vaibhavjain30699"
        profile = Profile(
            "vaibhavjain30699",
            "Vaibhav Jain",
            null,
            "ABV-Indian Institute of Information Technology & Management",
            45,
            49,
            35,
            "https://avatars.githubusercontent.com/u/26932479?v=4",
            null
        )
        profileResponse = Response.success(profile)
        githubViewModel.profile.observeForever(profileObserver)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun checkGithubUserName() = runBlockingTest {
        githubViewModel.profile.observeForever {}
//        CoroutineScope(Dispatchers.Default).launch {
//            githubViewModel.getProfileDetails(user)
//            val response = githubViewModel.profile.value
//            assertEquals(profile, response)
//        }
        githubViewModel.getProfileDetails(user)
        val response = githubViewModel.profile.value
        assertEquals(profile, response)
    }
}

open class FakeGithubRepositoryImpl :
    GithubRepository {

    private val profile = Profile(
        "vaibhavjain30699",
        "Vaibhav Jain",
        null,
        "ABV-Indian Institute of Information Technology & Management",
        45,
        49,
        35,
        "https://avatars.githubusercontent.com/u/26932479?v=4",
        null
    )

    override suspend fun getProfileDetails(user: String) = Response.success(profile)

    override suspend fun getRepositoriesForUser(user: String) =
        Response.success(emptyList<Repository>())

    override suspend fun getContributorsForRepository(
        user: String,
        repo: String
    ) = Response.success(emptyList<Contributor>())
}
