# 🔋 Battery Guardian

## 📱 Sobre o Projeto

**Battery Guardian** é um aplicativo Android desenvolvido para resolver um problema real: ajudar usuários cujo botão de power do celular está com defeito. Quando a bateria descarrega completamente e o dispositivo desliga, torna-se extremamente difícil ligá-lo novamente sem um botão de power funcionando.

A solução? Um serviço em segundo plano que monitora constantemente o nível da bateria e envia notificações quando ela atinge 20%, alertando o usuário para colocar o celular no carregador antes que seja tarde demais.

## ✨ Funcionalidades

### Implementadas
- ✅ **Monitoramento contínuo da bateria** - Serviço em segundo plano que acompanha o nível de carga
- ✅ **Notificações inteligentes** - Alerta quando a bateria chega em 20% e o celular não está carregando
- ✅ **Serviço foreground** - Garante que o monitoramento não seja interrompido pelo sistema
- ✅ **Detecção de carregamento** - Verifica se o dispositivo está conectado ao carregador antes de notificar
- ✅ **Notificações com vibração** - Feedback tátil para garantir que o usuário perceba o alerta

### Em Desenvolvimento
- 🔄 Interface visual com estatísticas da bateria
- 🔄 Saúde da bateria (battery health)
- 🔄 Média de consumo de energia
- 🔄 Histórico de carregamento
- 🔄 Configurações personalizáveis (nível de alerta, frequência de notificações)

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java
- **Platform:** Android SDK
- **Serviços:** 
  - Foreground Service
  - BroadcastReceiver para monitoramento de bateria
  - NotificationManager para alertas
- **Permissões:**
  - `POST_NOTIFICATIONS` - Para enviar notificações
  - `FOREGROUND_SERVICE` - Para manter o serviço ativo
  - `FOREGROUND_SERVICE_DATA_SYNC` - Para sincronização de dados

## 📋 Como Funciona

1. **Inicialização:** O app inicia um serviço em segundo plano assim que é aberto
2. **Monitoramento:** O serviço registra um `BroadcastReceiver` que escuta mudanças no estado da bateria
3. **Verificação:** A cada mudança, o serviço verifica:
   - Nível atual da bateria
   - Status de carregamento
4. **Notificação:** Quando a bateria atinge 20% **E** o celular **não está carregando**, uma notificação é enviada
5. **Persistência:** O serviço continua rodando em segundo plano, mesmo com o app fechado

## 🚀 Instalação

### Pré-requisitos
- Android Studio
- SDK Android (API 26 ou superior recomendado)
- Dispositivo Android ou Emulador

### Passos
1. Clone o repositório:
```bash
git clone https://github.com/jessemp3/Desenvolvimento-Android-Course.git
cd Desenvolvimento-Android-Course/Batteria
```

2. Abra o projeto no Android Studio

3. Sincronize as dependências do Gradle

4. Execute o aplicativo em um dispositivo ou emulador

## 💡 Uso

1. Ao abrir o app, o serviço de monitoramento é iniciado automaticamente
2. Uma notificação permanente indica que o serviço está ativo
3. Quando a bateria atingir 20% (e o celular não estiver carregando), você receberá uma notificação de alerta
4. O serviço continuará monitorando mesmo se você fechar o app

## 📂 Estrutura do Projeto

```
Batteria/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/jesse/batteria/
│   │   │   │   ├── MainActivity.java          # Activity principal
│   │   │   │   └── service/
│   │   │   │       └── BatteryService.java    # Serviço de monitoramento
│   │   │   ├── res/                           # Recursos (layouts, strings, etc)
│   │   │   └── AndroidManifest.xml            # Configurações e permissões
│   │   ├── androidTest/                       # Testes instrumentados
│   │   └── test/                              # Testes unitários
│   └── build.gradle.kts
└── README.MD
```

## 🎯 Motivação

Este projeto nasceu de uma necessidade real. Com o botão de power quebrado, cada vez que o celular descarregava completamente, era necessário encontrar soluções alternativas complexas para ligá-lo novamente. O Battery Guardian previne esse problema, garantindo que o usuário sempre tenha tempo de conectar o carregador antes que seja tarde demais.

## 🤝 Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para:
- Reportar bugs
- Sugerir novas funcionalidades
- Enviar pull requests

## 📝 Licença

Este projeto faz parte do curso de Desenvolvimento Android e está disponível para fins educacionais.

## 👨‍💻 Autor

Desenvolvido por **Jesse** como parte do curso de Desenvolvimento Android.

---

⚡ **Dica:** Mantenha sempre uma bateria externa por perto como backup adicional!
