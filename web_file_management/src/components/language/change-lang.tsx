import { Box, Button } from "@mui/material";
import vietnam from '../../assets/image/vietnam.png'
import us from '../../assets/image/world.png'

export function ChangeLanguage() {
    const changeLanguage = () => {

        if(localStorage.getItem('lang') === 'vi') {

            localStorage.setItem('lang', 'en');
        } else {

            localStorage.setItem('lang', 'vi');
        }
        window.location.reload();
    };
  return (
    <>
      <Box>
        <Button onClick={() => changeLanguage()}>
            {
                localStorage.getItem('lang') === 'vi' ?
                <img src={vietnam} width={30}/>
                :
                <img src={us} width={30}/>
            }
        </Button>
      </Box>
    </>
  );
}