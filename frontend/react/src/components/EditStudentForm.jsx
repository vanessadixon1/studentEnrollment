import { Formik, Form, useField } from 'formik';
import * as Yup from 'yup';
import {Alert, FormLabel, Input, AlertIcon, Select, Box, Button, Stack, Text} from "@chakra-ui/react";
import {updateStudent} from "../services/client.js";
import {errorNotification, successNotification} from "../services/Notification.js";

const MyTextInput = ({ label, ...props }) => {

    const [field, meta] = useField(props);
    return (
        <Box>
            <FormLabel htmlFor={props.id || props.name}>{label}</FormLabel>
            <Input className="text-input" {...field} {...props} />
            {meta.touched && meta.error ? (
                <Alert className="error" status={"error"} mt={2}>
                    <AlertIcon/>
                    {meta.error}</Alert>
            ) : null}
        </Box>
    );
};

const EditStudentForm = ({fetchStudents, id, name, email, phoneNumber, closePopOver}) => {
    const phoneRegExp = "/^((\\\\+[1-9]{1,4}[ \\\\-]*)|(\\\\([0-9]{2,3}\\\\)[ \\\\-]*)|([0-9]{2,4})[ \\\\-]*)*?[0-9]{3,4}?[ \\\\-]*[0-9]{3,4}?$/"
    const emailRegExp = "/[a-zA-Z][a-zA-Z0-9]+@(?:[a-zA-Z0-9]+\\.)+[A-Za-z]+$/"
    return (
        <>
            <Formik
                initialValues={{
                    firstName: name.split(" ")[0],
                    lastName: name.split(" ")[1].trim(),
                    email: email.trim(),
                    phoneNumber: phoneNumber
                }}
                validationSchema={Yup.object({
                    firstName: Yup.string()
                        .max(15, 'Must be 15 characters or less'),
                    lastName: Yup.string()
                        .max(15, 'Must be 15 characters or less'),
                    email: Yup.string()
                        .matches({emailRegExp},"Please enter a valid email"),
                    phoneNumber: Yup.string()
                        .matches({phoneRegExp},"Phone number is not valid"),
                })}

                onSubmit={(student, { setSubmitting, resetForm}) => {
                    setSubmitting(true)
                    updateStudent({id}, student).then(res => {
                        successNotification("Student Updated",`${name} updated to ${student.firstName} ${student.lastName} successfully`)
                        closePopOver()
                        fetchStudents()
                    }).catch(err => {
                        errorNotification(err.code, err.response.data.message)
                    }).finally(() => {
                        setSubmitting(false)
                    })
                }}
            >
                {({dirty}) => (
                    <Form>
                        <Text mb={5} ml={50}>Update Student</Text>
                        <Stack spacing={"24px"}>
                            <MyTextInput
                                label="First Name"
                                name="firstName"
                                type="text"
                                placeholder="Jane"
                            />

                            <MyTextInput
                                label="Last Name"
                                name="lastName"
                                type="text"
                                placeholder="Doe"
                            />
                            <MyTextInput
                                label="Email Address"
                                name="email"
                                type="email"
                                placeholder="jane@formik.com"
                            />

                            <MyTextInput
                                label="Phone Number"
                                name="phoneNumber"
                                type="text"
                                placeholder="Contact Number"
                            />

                            <Button isDisabled={!dirty} type="submit">Submit</Button>
                        </Stack>
                    </Form>
                )}
            </Formik>
        </>
    );
};

export default EditStudentForm