import { TextField } from "@mui/material";
import { Controller } from "react-hook-form";

export interface ITextFieldProps {
    name: string,
    control: any,
    value?: any,
    label?: string,
}

export function ITextField(props: ITextFieldProps) {
  return (
    <Controller 
        name={props.name}
        control={props.control}
        render={({field, fieldState}) => (
            <TextField 
                {...field}
                label={props.label}
                error={!!fieldState.error}
                helperText={fieldState.error?.message}
                fullWidth />
        )}
    />
  );
}