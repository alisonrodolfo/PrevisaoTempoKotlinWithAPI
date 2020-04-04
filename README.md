Previsao Tempo Kotlin With API

As informações devem ser recuperadas de um Web Services de terceiros ativo (ver lista em: https://www.programmableweb.com/) 
ou serviço de base de dados remota (por exemplo, usando Firebase ou outro SDK); ✅

Deve ter uma lista de entidades padrão (pré-baixadas) no aplicativo (por exemplo, na aplicação de previsão de tempo já 
abre com as cidades Barcelona, Dublin, Londres, Nova York); ✅

O usuário deve poder adicionar uma nova entidade a partir do web services (por exemplo, uma nova cidade); ✅

O usuário deve poder remover uma entidade da lista (por exemplo, remover uma cidade da lista de cidades); ✅

Todas as alterações devem ser persistentes. Se o usuário adicionar ou remover uma entidade, a alteração deverá persistir 
quando o aplicativo for reiniciado; ✅

Os dados que já foram recuperados do Web Services também devem ser persistidos localmente de modo que o usuário possa 
acessar esses dados mesmo sem conexão com a Internet; ✅

O aplicativo deve apresentar os dados de entidade em dois modos (retrato e paisagem) de exibição gráfica com Views 
diferentes (por exemplo, mostra a previsão de tempo de uma cidade numa tabela no modo retrato e um gráfico no modo paisagem); ✅

Utilizar pelo menos um componente (JetPack) da arquitetura de referencia do guia do desenvolvedor 
(https://developer.android.com/jetpack/docs/guide).  Recomenda-se usar um modelo de arquitetura 
pronta: https://github.com/android/architecture-samples ou https://github.com/android/architecture-samples/branches ✅


```kotlin
 internal fun getCurrentData() {
        val retrofit = Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val service = retrofit.create(WeatherService::class.java)
        val call = service.getCurrentWeatherData(if (title.isNotEmpty()) title else "João Pessoa", lang, units, AppId)
        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.code() == 200) {
                    val weatherResponse = response.body()!!

                    tempmax = "" + weatherResponse.main!!.temp_max +"º max"
                    tempmin = "" + weatherResponse.main!!.temp_min +"º min"
                    temperatura = "Temp: " + weatherResponse.main!!.temp +"º " + weatherResponse.weather[0].description
                    previsao = "" + weatherResponse.weather[0].description
                    

                    probchuvahum = "Probabilidade de chuva: "+ weatherResponse.clouds!!.all+"% - umidade: "+ weatherResponse.main!!.humidity+"%"
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {

            }
        })
    }
```



# Tela

<p align="center">
	<br>
	<img src="app/src/main/assets/print.PNG"/ >
      <br>
</p>


<p align="center">
<img src="https://github.com/googlesamples/android-architecture/wiki/images/aab-logov2.png" alt="Illustration by Virginia Poltrack"/>
</p>

Android Architecture Blueprints is a project to showcase different architectural approaches to developing Android apps. In its different branches you'll find the same app (a TODO app) implemented with small differences.

In this branch you'll find:
*   Kotlin **[Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)** for background operations.
*   A single-activity architecture, using the **[Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started)** to manage fragment operations.
*   A presentation layer that contains a fragment (View) and a **ViewModel** per screen (or feature).
*   Reactive UIs using **LiveData** observables and **Data Binding**.
*   A **data layer** with a repository and two data sources (local using Room and remote) that are queried with one-shot operations (no listeners or data streams).
*   Two **product flavors**, `mock` and `prod`, [to ease development and testing](https://android-developers.googleblog.com/2015/12/leveraging-product-flavors-in-android.html) (except in the Dagger branch).
*   A collection of unit, integration and e2e **tests**, including "shared" tests that can be run on emulator/device or Robolectric.

## Variations

This project hosts each sample app in separate repository branches. For more information, see the `README.md` file in each branch.

### Stable samples - Kotlin
|     Sample     | Description |
| ------------- | ------------- |
| [master](https://github.com/googlesamples/android-architecture/tree/master) | The base for the rest of the branches. <br/>Uses Kotlin, Architecture Components, coroutines, Data Binding, etc. and uses Room as source of truth, with a reactive UI. |
| [dagger-android](https://github.com/googlesamples/android-architecture/tree/dagger-android)<br/>[[compare](https://github.com/googlesamples/android-architecture/compare/dagger-android#files_bucket)] | A simple Dagger setup that uses `dagger-android` and removes the two flavors. |
| [usecases](https://github.com/googlesamples/android-architecture/tree/usecases)<br/>[[compare](https://github.com/googlesamples/android-architecture/compare/usecases#files_bucket)] | Adds a new domain layer that uses UseCases for business logic. |



**Note:** To review a different sample, replace `usecases` with the name of sample you want to check out.

Finally open the `android-architecture/` directory in Android Studio.

### License


```
Copyright 2019 Google, Inc.

Licensed to the Apache Software Foundation (ASF) under one or more contributor
license agreements. See the NOTICE file distributed with this work for
additional information regarding copyright ownership. The ASF licenses this
file to you under the Apache License, Version 2.0 (the "License"); you may not
use this file except in compliance with the License. You may obtain a copy of
the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
License for the specific language governing permissions and limitations under
the License.
```
