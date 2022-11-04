package com.example.chap6top5.viewmodel

import com.example.chap6top5.model.DataMahasiswa
import com.example.chap6top5.model.ResponseBookmarkItem
import com.example.chap6top5.model.ResponseDataMhs
import com.example.chap6top5.model.ResponseDataMhsItem
import com.example.chap6top5.network.APIInterface
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Call

class ViewModelDataMhsTest{
    lateinit var servis : APIInterface
    @Before
    fun setUp(){
        servis = mockk()
    }

    @Test
    fun getDataMhsTest(): Unit = runBlocking {
//        mocking GIVEN
        val respAllDataMhs = mockk <Call<List<ResponseDataMhsItem>>>()

        every {
            runBlocking {
                servis.getAllDataMhs()
            }
        } returns respAllDataMhs

//        System Under Test (WHEN)
        val result = servis.getAllDataMhs()

        verify {
            runBlocking { servis.getAllDataMhs() }
        }
        assertEquals(result, respAllDataMhs)
    }
    @Test
    fun testGetDataMhs(){
        val respAllDataMhs = mockk <Call<List<ResponseDataMhsItem>>>()
        every {
            servis.getAllDataMhs()

        }returns respAllDataMhs

        //        System Under Test (WHEN)
        val result = servis.getAllDataMhs()

        verify {
            servis.getAllDataMhs()
        }
        Assert.assertEquals(result, respAllDataMhs)
    }
    @Test
    fun testGetBookmark(){
        val respBookmark = mockk <Call<List<ResponseBookmarkItem>>>()
        every {
            servis.getBookmarkMhs()

        }returns respBookmark

        //        System Under Test (WHEN)
        val result = servis.getBookmarkMhs()

        verify {
            servis.getBookmarkMhs()
        }
        Assert.assertEquals(result, respBookmark)
    }
    @Test
    fun testBookmark(){
        val respBookmark = mockk <Call<List<ResponseBookmarkItem>>>()
        every {
            servis.getBookmarkMhs()

        }returns respBookmark

        //        System Under Test (WHEN)
        val result = servis.getBookmarkMhs()

        verify {
            runBlocking { servis.getBookmarkMhs() }
        }
        Assert.assertEquals(result, respBookmark)
    }
    @Test
    fun testDeleteData(){
        val id = 0
        val resDelete = mockk <Call<ResponseDataMhsItem>>()
        every {
            servis.deleteDataMhs(id)

        }returns resDelete

        //        System Under Test (WHEN)
        val result = servis.deleteDataMhs(id)

        verify {
            runBlocking { servis.deleteDataMhs(id) }
        }
        Assert.assertEquals(result, resDelete)
    }
    @Test
    fun testDeleteBookmark(){
        val id = 0
        val resDeleteBm = mockk <Call<ResponseBookmarkItem>>()
        every {
            servis.deleteBookmark(id)

        }returns resDeleteBm

        //        System Under Test (WHEN)
        val result = servis.deleteBookmark(id)

        verify {
            runBlocking { servis.deleteBookmark(id) }
        }
        Assert.assertEquals(result, resDeleteBm)
    }
//    @Test
//    fun testEditData(){
//        val id = 0
//        val resEdit = mockk <Call<ResponseDataMhsItem>>()
//        every {
//            servis.editDataMhs(id,DataMahasiswa("dapin","20SI1212","jakarta","null","laki laki"))
//        }returns resEdit
//
//        val result = servis.editDataMhs(id,DataMahasiswa("dapin","20SI1212","jakarta","null","laki laki"))
//        verify {
//            servis.editDataMhs(id,DataMahasiswa("dapin","20SI1212","jakarta","null","laki laki"))
//        }
//        assertEquals(result, resEdit)
//    }

}