import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-chatbot',
  templateUrl: './chatbot.component.html',
  styleUrls: ['./chatbot.component.css'],
})
export class ChatbotComponent implements OnInit {
  isChatVisible = false;

  ngOnInit() {
    // Initialize Botpress Webchat
    if (window.botpress) {
      window.botpress.init({
        botId: '8e6d19fe-ceea-4b8f-b962-aa5f6a0a5d21',
        configuration: {
          botName: 'Herb Guide',
          botAvatar: 'https://files.bpcontent.cloud/2025/01/04/12/20250104121042-X1WUV98D.png',
          website: {},
          email: {},
          phone: {},
          termsOfService: {},
          privacyPolicy: {},
          color: '#C1CFA1',
          variant: 'solid',
          themeMode: 'light',
          fontFamily: 'inter',
          radius: 4,
        },
        clientId: '2c0895bd-0702-4dd6-86e4-deb3749b1025',
      });
    }
  }

  toggleChat() {
    this.isChatVisible = !this.isChatVisible;
    window.botpress.sendEvent({ type: this.isChatVisible ? 'show' : 'hide' });
  }
}
