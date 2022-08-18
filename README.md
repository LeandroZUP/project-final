<p align="center">
<img src="https://user-images.githubusercontent.com/101604752/185274026-8923b8a5-6f84-48c5-b561-d84a0e44c356.png"/>
</p>

<h4 align="center"> 
    :rocket:  App  para Zupper com informações do Onboarding e dos projetos   :rocket:
</h4>

# HelloZupper


### Resumo do Projeto

Projeto Final de app feito de Zuppers do Projeto Catalisa para Zuppers do Onboarding. Tem como referência o app da [ZupCon 2022](https://apkcombo.com/pt/zupcon-2022/com.inteegra.zupcon2022/) e a ZupNet.

### Índices

* [Resumo do Projeto](#resumo-do-projeto)
* [Índices](#índices)
* [Apresentação](#apresentação) (Público-Alvo, Objetivo, Referências)
* [Características](#características-do-app)
* [Funcionalidade](#funcionalidade)
* [Gradle](#gradle)
* [Changelog](#changelog)
* [Limitações](#limitações)
* [Futuras Implementações](#futuras-implementações)
* [Conclusões](#conclusões)
* [Developers envolvides](#developers-envolvides)

## Apresentação

O aplicativo é resultado do projeto final dos Zuppers do Projeto Catalisa 3. Ele tem com objetivo facilitar a vida de Zuppers do Onboarding tendo como principais facilidades centralizar um mar de informações importantes que Zuppers encontram ao ingressar na ZUP e torná-las disponíveis a uma distância de alguns cliques. É uma tentativa de criar uma versão de ZupNet mobile.

A ideia é o app consumir a mesma base da ZupNet, evitando retrabalho àqueles que lidam diretamente com o conteúdo do site e divulgação.

## Características do App
* Línguagem empregada: [Kotlin 1.7](https://kotlinlang.org/)
* IDE: [Android Studio](https://developer.android.com/studio)
* Padrão de Arquitetura: [Android MVVM Design Pattern](https://medium.com/android-dev-br/arquiteturas-em-android-mvvm-kotlin-android-architecture-components-databinding-lifecycle-d5e7a9023cf3)

## Funcionalidade

`Tela Home` É exibido aqui a home de HelloZupper

https://user-images.githubusercontent.com/101604752/185468518-11561b52-020f-49e4-a93a-af8ac9bce292.mp4


`Tela Pilares` É exibido aqui os 4 principais pilares da ZUP

https://user-images.githubusercontent.com/101604752/185468530-b4f0c190-6725-4066-b59a-92bbb9cbfb26.mp4

`Tela Benefícios` É exibido aqui os benefícios que a pessoa Zupper possuí.

https://user-images.githubusercontent.com/101604752/185468577-d82b2336-1165-427f-a6e9-46bd71406864.mp4

`Tela FeedNews` É exibido aqui os comunicados recebidos no espaço do Google Chat da Zup '(❌ Updates: não comentar!)', com ferramenta de busca e marcação

https://user-images.githubusercontent.com/101604752/185468760-63fd1662-4558-48ec-8782-1f22b117369c.mp4

`Tela Programas` É exibido aqui os 4 principais programas de recrutamento da ZUP

https://user-images.githubusercontent.com/101604752/185468832-0f7de7d3-9c5e-44c7-9977-b3db6bc31550.mp4

`Tela Projetos` Será exibido aqui a futura tela de implementação e o `Fale com Tio CAZ`

## Gradle

```
dependencies {
	...
	// :::: LIFECYCLE ::::
	implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1'
	implementation 'androidx.annotation:annotation:1.4.0'
	implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.4.1'

	// :::: GSON ::::
	implementation 'com.google.code.gson:gson:2.9.0'
	implementation 'com.squareup.retrofit2:converter-gson:2.6.0'

	// :::: ROOM DATABASE ::::
	implementation 'androidx.room:room-runtime:2.4.2'
	kapt 'androidx.room:room-compiler:2.4.2'

	// :::: NETWORK CONNECTIONS ::::
	implementation 'com.squareup.retrofit2:retrofit:2.9.0'
	implementation 'com.squareup.okhttp3:logging-interceptor:4.2.1'

	// :::: FIREBASE ::::
	implementation platform ('com.google.firebase:firebase-bom:30.3.2')
	implementation 'com.google.firebase:firebase-analytics-ktx'
	implementation 'com.google.firebase:firebase-auth-ktx'
	
	// :::: PICASSO IMAGE LOADER ::::
	implementation 'com.squareup.picasso:picasso:2.71828'

	// :::: LOTTIE ::::
	implementation 'com.airbnb.android:lottie:3.4.0'
}
```

## HowTo 
* Rodar o app 
No terminal, clone o projeto e teste na branch `main`: 
  `git clone https://github.com/LeandroZUP/project-final`

* API GSON 
[API dos Pilares](https://62f116c825d9e8a2e7c5a943.mockapi.io/hellozupperapi/pillars)
[API dos Pilares](https://62f116c825d9e8a2e7c5a943.mockapi.io/hellozupperapi/feed)
[API dos Pilares](https://62f116c825d9e8a2e7c5a943.mockapi.io/hellozupperapi/benefits)
[API dos Pilares](https://62f116c825d9e8a2e7c5a943.mockapi.io/hellozupperapi/programs)

* Protótipo do App
  `https://www.figma.com/file/xOyBekXSFJcThGDOOuRJTC/Splash?node-id=67%3A1175`

## Changelog
* Versão do App 1.0.1 - Initial release

## Futuras Implementações
* TI Corp Service
* Payroll Ops
* Compliance
* Home Office Best Practices
* Organograma da Squad

## Conclusões
É possível expandir as funcionalidades do app para melhorar ainda mais a experiência de integrantes Zuppers, com mais informações que normalmente são passadas na semana de `Onboarding`, chegando a ser também útil às pessoas Zuppers que estão com mais tempo de casa.

## Developers Envolvides

##### Pessoas Desenvolvedoras de Backend Kotlin
[<img src="https://user-images.githubusercontent.com/101604752/185282925-32dd93e4-da64-448d-8716-fe0f02791b36.png" width=30><br><sub>Daniel Caitano</sub>](https://github.com/danielcaitano)

[<img src="https://user-images.githubusercontent.com/101604752/185282935-e91e72b8-19a4-40fd-b8fa-f656896df672.png" width=30><br><sub>Leandro Miguel</sub>](https://github.com/LeandroZUP)

[<img src="https://user-images.githubusercontent.com/101604752/185282963-64a0a982-d791-4522-bfec-e182fad360ef.png" width=30><br><sub>Luiz Rodrigues</sub>](https://github.com/luizpaulo451)

[<img src="https://user-images.githubusercontent.com/101604752/185282986-8dfb2d58-e6d3-4e09-a83b-9105a83ee38c.png" width=30><br><sub>Tamires Cameijo</sub>](https://github.com/tamirescamejo2)

##### Product Owner
[<img src="https://user-images.githubusercontent.com/101604752/185475128-c868c3bb-a04c-44e2-9807-97cd528d2c48.jpg" width=30><br><sub>Ivo Albuquerque</sub>](https://github.com/danielcaitano)

##### Team Leader
[<img src="https://user-images.githubusercontent.com/101604752/185477785-3d2ce40f-f7c6-4efd-a995-0a069214c185.jpg" width=30><br><sub>Jessica Milena Corrêa</sub>](https://github.com/JessicaMilenaCorrea)

[<img src="https://user-images.githubusercontent.com/101604752/185478201-f94f1f67-9084-4bbe-8a28-18e797b788f9.png" width=30><br><sub>Felipe de Araujo Fernandes</sub>](https://github.com/FepaZUP)

##### Scrum Master
[<img src="https://user-images.githubusercontent.com/101604752/185477451-f71cbc29-b108-453d-93f7-7a53fd210b6b.jpg" width=30><br><sub>Carolina Queiroz</sub>](https://github.com/carolina-queiroz)
