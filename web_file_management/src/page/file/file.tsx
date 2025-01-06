import { Box, Button, Container, Grid2, IconButton, TextField } from "@mui/material";
import UploadIcon from '@mui/icons-material/Upload';
import DeleteIcon from '@mui/icons-material/Delete';
import { useAppSelector } from "../../redux/hooks";
import { useEffect, useState } from "react";
import { getFiles } from "../../api/FileService";

export interface IFilePageProps {
}

interface ResponseFileDataModel {
    fileId: number,
    filePath: String,
    fileName: String
}

interface ResponseFileData {
    datas: ResponseFileDataModel[]
}

export function FilePage(props: IFilePageProps) {

    const jwt = useAppSelector(state => state.auth.user);
    const [fileData, setFileData] = useState<ResponseFileData>();
    const [reload, setReload] = useState<false>(false);

    const fetchFileApi = async () => {

        const res = await getFiles(jwt);
        if (res.status === 200) {

            setFileData({ datas: res.data.data });
            console.log(res);
        }
    };
    useEffect(() => {
        fetchFileApi();
    }, [reload]);
    return (
        <>
            <Container className="con-pt-1">
                <Box sx={{ pb: 2 }}>
                    <Button variant="contained" endIcon={<UploadIcon />}>
                        UPLOAD
                    </Button>
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