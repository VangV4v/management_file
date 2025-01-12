import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import UploadIcon from '@mui/icons-material/Upload';
import { useRef, useState } from 'react';
import { useTranslation } from 'react-i18next';
import { Box } from '@mui/material';
import defaultImg from '../../assets/image/noimage.png';
import { useAppSelector } from '../../redux/hooks';
import { useForm } from 'react-hook-form';
import { uploadFile } from '../../api/FileService';

export default function UploadFormDialog() {

    const [open, setOpen] = useState(false);
    const { t } = useTranslation();
    const [imagePath, setImagePath] = useState(defaultImg);
    const jwt = useAppSelector(state => state.auth.user);
    const btnSubmitRef = useRef<HTMLButtonElement>(null);

    const handleClickOpen = () => {
        setOpen(true);
    };
    const handleClose = () => {
        setOpen(false);
    };
    const form = useForm();
    const handleUpload = (data) => {

        const configHeader = {
            'Authorization': jwt,
            'Content-Type': data.file[0].type
        };
        const formData = new FormData();
        formData.append("file", data.file[0]);
        uploadFile(configHeader, formData)
            .then(res => {
                if (res.status === 200) {
                    handleClose();
                    window.location.replace('/file');
                }
            }).catch(err => {
                console.log(err);
            });
    };

    return (
        <>
            <Button variant="contained" onClick={handleClickOpen} endIcon={<UploadIcon />}>
                {t('content.upload')}
            </Button>
            <Dialog
                open={open}
                onClose={handleClose}
            >
                <DialogTitle className='center'>
                    <form onSubmit={form.handleSubmit(handleUpload)}>
                        <input type='file' {...form.register("file")} onChange={(e) => {
                            setImagePath(URL.createObjectURL(e.target.files?.item(0)))
                        }} />
                        <Button sx={{ display: 'none' }} type='submit' ref={btnSubmitRef}></Button>
                    </form>
                </DialogTitle>
                <DialogContent>
                    <Box>
                        <img src={imagePath} />
                    </Box>
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose}>{t('content.cancel')}</Button>
                    <Button type="submit" onClick={() => { btnSubmitRef.current?.click(); }}>{t('content.save')}</Button>
                </DialogActions>
            </Dialog>
        </>
    );
}
