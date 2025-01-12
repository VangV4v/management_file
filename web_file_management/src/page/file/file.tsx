import { Box, Container, Grid2, IconButton, Input, Stack, TextField } from "@mui/material";
import DeleteIcon from '@mui/icons-material/Delete';
import { useAppSelector } from "../../redux/hooks";
import { useEffect, useRef, useState } from "react";
import { getFiles } from "../../api/FileService";
import { useTranslation } from "react-i18next";
import { BackDropPage } from "../../components/backdrop/back-drop";
import UploadFormDialog from "../upload/upload-file";
import SearchIcon from '@mui/icons-material/Search';

export interface IFilePageProps {
}

interface ResponseFileDataModel {
    fileId: number,
    filePath: string,
    fileName: string
}

interface ResponseFileData {
    datas: ResponseFileDataModel[]
}

export function FilePage(props: IFilePageProps) {

    const jwt = useAppSelector(state => state.auth.user);
    const [fileData, setFileData] = useState<ResponseFileData>();
    const [reload, setReload] = useState<false>(false);
    const ref = useRef<any>();
    const [backDrop, setBackDrop] = useState<boolean>(false);
    const [fileName, setFileName] = useState<string>('');
    const inputSearchRef = useRef<HTMLInputElement>();
    const delayLoad = () => {
        setBackDrop(true);
        ref.current = setTimeout(() => {

            setBackDrop(false);
        }, 1000);
    }

    const fetchFileApi = async () => {

        const res = await getFiles(jwt, {name: fileName});
        if (res.status === 200) {

            setFileData({ datas: res.data.data });
        }
    };
    useEffect(() => {
        delayLoad();
        fetchFileApi();
    }, [reload]);
    return (
        <>
            <Container className="con-pt-1">
                <BackDropPage open={backDrop}></BackDropPage>
                <Box sx={{ pb: 2 }}>
                    <Grid2 container>
                        <Grid2 size={{ xs: 12, xl: 6, sm: 6 }} sx={{ pb: 1 }}>
                            <UploadFormDialog></UploadFormDialog>
                        </Grid2>
                        <Grid2 size={{ xs: 12, xl: 6, sm: 6 }}>
                            <Box className="right">
                                <Input size="small" ref={inputSearchRef} onChange={(e) => setFileName(e.target.value)} endAdornment={
                                    <IconButton onClick={() => {setReload(!reload)}}><SearchIcon /></IconButton>
                                } />
                            </Box>
                        </Grid2>
                    </Grid2>

                </Box>
                <Box>
                    <Grid2 container spacing={2}>
                        {
                            fileData?.datas.map(item => (
                                <Grid2 size={{ xs: 12, xl: 2, sm: 3 }} key={item.fileId}>
                                    <Box>
                                        <img src={item.filePath} className="img" />
                                    </Box>
                                    <Box>
                                        <TextField value={item.fileName} size="small"
                                            slotProps={{
                                                input: {
                                                    endAdornment: <IconButton><DeleteIcon /></IconButton>
                                                }
                                            }}
                                        />
                                    </Box>
                                </Grid2>
                            ))
                        }

                    </Grid2>
                </Box>
            </Container>
        </>
    );
}