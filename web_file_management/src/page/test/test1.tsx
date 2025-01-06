import { useTranslation } from "react-i18next";

export function Test1Page() {
  const { t } = useTranslation();
  return (
    <div>
      A : {t('content.text')}
    </div>
  );
}