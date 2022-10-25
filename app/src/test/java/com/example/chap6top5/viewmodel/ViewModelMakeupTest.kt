package com.example.chap6top5.viewmodel

import com.example.chap6top5.model.ResponseDataMakeupItem
import com.example.chap6top5.network.RestfulApi
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import retrofit2.Call

class ViewModelMakeupTest {
    lateinit var servis : RestfulApi
    @Before
    fun setUp(){
        servis = mockk()
    }

    @Test
    fun getMakeUpTest(): Unit = runBlocking {
//        mocking GIVEN
    val respAllMakeup = mockk <Call<List<ResponseDataMakeupItem>>>()

    every {
        runBlocking {
            servis.getAllMakeup()
        }
    }returns respAllMakeup

//        System Under Test (WHEN)
    val result = servis.getAllMakeup()

    verify {
        runBlocking { servis.getAllMakeup() }
    }
    Assert.assertEquals(result, respAllMakeup)

    }
    @Test
    fun testGetMakeUp(){
        val respAllMakeup = mockk <Call<List<ResponseDataMakeupItem>>>()
        every {
            servis.getAllMakeup()

        }returns respAllMakeup

        //        System Under Test (WHEN)
        val result = servis.getAllMakeup()

        verify {
            servis.getAllMakeup()
        }
        Assert.assertEquals(result, respAllMakeup)
    }
}