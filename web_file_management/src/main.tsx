import { createRoot } from 'react-dom/client'
import './index.css'
import { BrowserRouter } from "react-router";
import { Provider } from 'react-redux'
import { store } from './redux/store.ts'
import App from './App.tsx';
import { I18nextProvider } from 'react-i18next';
import i18n from './translation/i18n.ts';

createRoot(document.getElementById('root')!).render(
  <Provider store={store}>
    <BrowserRouter>
      <I18nextProvider i18n={i18n}>
        <App />
      </I18nextProvider>
    </BrowserRouter>
  </Provider>
)