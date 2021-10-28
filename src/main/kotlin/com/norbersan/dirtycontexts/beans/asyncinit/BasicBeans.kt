package com.norbersan.dirtycontexts.beans.asyncinit

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy
import kotlin.random.Random

abstract class AbstractAsyncInitBean(){
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
        status = Status.SETTINGUP
        println("==> @PostConstruct method called for bean of type ${javaClass.simpleName}")
        status = Status.READY
    }

    @PreDestroy
    fun teardown(){
        println("==> @PreDestroy method called for bean of type ${javaClass.simpleName}")
    }
}

@Component
@Profile("asyncinit")
class AsyncInitBeanA() : AbstractAsyncInitBean(){}

@Component
@Profile("asyncinit")
class AsyncInitBeanB() : AbstractAsyncInitBean(){}

@Component
@Profile("asyncinit")
class AsyncInitBeanC() : AbstractAsyncInitBean(){
}

@Component
@Profile("asyncinit")
class AsyncInitBeanContainer(){
    @Autowired
    lateinit var asyncInitBeanA: AsyncInitBeanA

    @Autowired
    lateinit var asyncInitBeanB: AsyncInitBeanB

    @Autowired
    lateinit var asyncInitBeanC: AsyncInitBeanC
}

enum class Status(){
    INSTANTIATING,
    INSTANTIATED,
    SETTINGUP,
    READY,
}