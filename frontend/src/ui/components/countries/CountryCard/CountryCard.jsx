import {useState} from "react";
import {Box, Button, Card, CardActions, CardContent, Typography} from "@mui/material";
import EditIcon from "@mui/icons-material/Edit";
import DeleteIcon from "@mui/icons-material/Delete";
import EditCountryDialog from "../EditCountryDialog/EditCountryDialog.jsx";
import DeleteCountryDialog from "../DeleteCountryDialog/DeleteCountryDialog.jsx";

const CountryCard = ({country, onEdit, onDelete}) => {

    const [editCountryDialog, setEditCountryDialog] = useState(false)
    const [deleteCountryDialog, setDeleteCountryDialog] = useState(false)

    return (
        <>
            <Card sx={{boxShadow: 3, borderRadius: 2, p: 1}}>
                <CardContent>
                    <Typography variant="h5">{country.name}</Typography>
                    <Typography variant="subtitle2">
                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab aperiam assumenda blanditiis cum
                        ducimus enim modi natus odit quibusdam veritatis.
                    </Typography>
                    <Typography variant="body1" fontWeight="bold"
                                sx={{textAlign: "center", fontSize: "1.25rem"}}>
                        {country.continent}

                    </Typography>
                </CardContent>
                <CardActions sx={{justifyContent: "space-between"}}>
                    <Box>
                        <Button
                            size="small"
                            color="warning"
                            startIcon={<EditIcon/>}
                            sx={{mr: "0.25rem"}}
                            onClick={() => setEditCountryDialog(true)}
                        >
                            Edit
                        </Button>
                        <Button
                            size="small"
                            color="error"
                            startIcon={<DeleteIcon/>}
                            onClick={() => setDeleteCountryDialog(true)}
                        >
                            Delete
                        </Button>
                    </Box>
                </CardActions>
            </Card>
            <EditCountryDialog
                open={editCountryDialog}
                onClose={() => setEditCountryDialog(false)}
                country={country}
                onEdit={onEdit}
            />
            <DeleteCountryDialog
                open={deleteCountryDialog}
                onClose={() => setDeleteCountryDialog(false)}
                country={country}
                onDelete={onDelete}
            />
        </>


    )

}

export default CountryCard;