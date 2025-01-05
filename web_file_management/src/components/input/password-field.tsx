import { IconButton, TextField } from "@mui/material";
import { Controller } from "react-hook-form";
import VisibilityIcon from '@mui/icons-material/Visibility';
import VisibilityOffIcon from '@mui/icons-material/VisibilityOff';
import { useState } from "react";

export interface IPasswordFieldProps {
    name: string,
    control: any,
    value?: any,
    label?: string,
}

export function IPasswordField(props: IPasswordFieldProps) {

    const [isShowPassword, setShowPassword] = useState(false);

  return (
    <Controller 
        name={props.name}
        control={props.control}
        render={({field, fieldState}) => (
            <TextField 
                {...field}
                type={isShowPassword ? 'text' : 'password'}
                label={props.label}
                error={!!fieldState.error}
                helperText={fieldState.error?.message}
                slotProps={{
                    input : {
                        endAdornment: (<IconButton onClick={() => setShowPassword(!isShowPassword)}> {!isShowPassword ? <VisibilityIcon/> : <VisibilityOffIcon/> } </IconButton>)
                    }
                }}
                fullWidth />
        )}
    />
  );
}