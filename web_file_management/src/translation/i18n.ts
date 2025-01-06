import i18n from 'i18next';
import Backend from 'i18next-http-backend';
import { initReactI18next } from 'react-i18next';
import translationVI from '../locales/vi/translation.json'
import translationEN from '../locales/en/translation.json'

const resources = {
    en: {
        translation: translationEN
    },
    vi: {
        translation: translationVI
    }
}

i18n
    .use(Backend)
    .use(initReactI18next)
    .init({
        resources,
        fallbackLng: localStorage.getItem('lang') || 'en',
        debug: true,
        interpolation: {
            escapeValue: false
        }
    });

export default i18n;