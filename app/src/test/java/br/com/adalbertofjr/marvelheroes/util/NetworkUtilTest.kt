package br.com.adalbertofjr.marvelheroes.util

import android.net.ConnectivityManager
import android.net.NetworkInfo
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NetworkUtilTest{
    @Mock
    private lateinit var connectivityManager: ConnectivityManager

    @Mock
    private lateinit var networkInfo: NetworkInfo

    @Test
    fun should_ReturnTrue_WhenIsConnected() {
        Mockito.`when`(connectivityManager.activeNetworkInfo).thenReturn(networkInfo)
        Mockito.`when`(networkInfo.isConnected).thenReturn(true)

        NetworkUtil.isConnected(connectivityManager)

        Mockito.verify(connectivityManager, Mockito.times(1)).activeNetworkInfo
        Mockito.verify(networkInfo, Mockito.times(1)).isConnected
        Assert.assertTrue(networkInfo.isConnected)
    }

    @Test
    fun should_ReturnFalse_WhenIsNotConnected() {
        Mockito.`when`(connectivityManager.activeNetworkInfo).thenReturn(networkInfo)
        Mockito.`when`(networkInfo.isConnected).thenReturn(false)

        NetworkUtil.isConnected(connectivityManager)

        Mockito.verify(connectivityManager, Mockito.times(1)).activeNetworkInfo
        Mockito.verify(networkInfo, Mockito.times(1)).isConnected
        Assert.assertFalse(networkInfo.isConnected)
    }

}