package com.vaibhav.githubclone

import com.vaibhav.githubclone.model.Profile
import com.vaibhav.githubclone.retrofitAPI.GithubRepository
import com.vaibhav.githubclone.retrofitAPI.GithubRepositoryImpl
import com.vaibhav.githubclone.retrofitAPI.RetrofitService
import com.vaibhav.githubclone.viewmodel.GithubViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

@RunWith(JUnit4::class)
class GithubViewModelTest {

    @Mock
    private lateinit var retrofitService: RetrofitService

    private lateinit var repository: GithubRepository
    private lateinit var githubViewModel: GithubViewModel
    lateinit var profile: Profile
    lateinit var user: String


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = GithubRepositoryImpl(retrofitService)
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
        runBlocking {
            val job = CoroutineScope(Dispatchers.IO).async {
                Mockito.`when`(retrofitService.getProfileDetails(user)).thenReturn(
                    Response.success(profile)
                )
            }
            job.await()
        }
    }

    @Test
    fun checkGithubUserName() {
        runBlocking {
            val job = CoroutineScope(Dispatchers.IO).async {
                githubViewModel.getProfileDetails(user)
            }
            job.await()
        }
        if (githubViewModel.profile.value != null)
            assert(githubViewModel.profile.value!!.userId == user)
    }
}
