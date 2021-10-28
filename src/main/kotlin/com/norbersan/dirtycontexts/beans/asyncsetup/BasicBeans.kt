package com.norbersan.dirtycontexts.beans.asyncsetup

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy
import kotlin.random.Random

abstract class AbstractAsyncSetupBean(){
    val initMilis = Random.nextLong(5000, 10000)
    var status = Status.INSTANTIATING
    init {
        Thread{
            TimeUnit.MILLISECONDS.sleep(initMilis)
            status = Status.INSTANTIATED
        }.start()
    }
    override fun toString() = "bean ${javaClass.simpleName}, status: $status, startup: $initMilis milis"

    @PostConstruct
    fun setup(){
        println("==> @PostConstruct started for bean of type ${javaClass.simpleName}")
        status = Status.SETTINGUP
        Thread{
            TimeUnit.MILLISECONDS.sleep(initMilis)
            status = Status.READY
            println("==> @PostConstruct finished for bean of type ${javaClass.simpleName}")
        }.start()
    }

    @PreDestroy
    fun teardown(){
        println("==> @PreDestroy method called for bean of type ${javaClass.simpleName}")
    }
}

@Component
@Profile("asyncsetup")
class AsyncSetupBeanA() : AbstractAsyncSetupBean(){}

@Component
@Profile("asyncsetup")
class AsyncSetupBeanB() : AbstractAsyncSetupBean(){}

@Component
@Profile("asyncsetup")
class AsyncSetupBeanC() : AbstractAsyncSetupBean(){
}

@Component
@Profile("asyncsetup")
class AsyncSetupBeanContainer(){
    @Autowired
    lateinit var asyncInitBeanA: AsyncSetupBeanA

    @Autowired
    lateinit var asyncInitBeanB: AsyncSetupBeanB

    @Autowired
    lateinit var asyncInitBeanC: AsyncSetupBeanC
}

enum class Status(){
    INSTANTIATING,
    INSTANTIATED,
    SETTINGUP,
    READY,
}