import {
    Button,
    Checkbox,
    Flex,
    FormControl,
    FormLabel,
    Heading,
    Input,
    Link,
    Text,
    Stack,
    Image, Box, Alert, AlertIcon,
} from '@chakra-ui/react';

import enrollment from '../../../public/maccomputer.jpg'
import {Formik, Form, useField} from "formik";
import  * as Yup from 'yup';
import {useAuth} from "../context/AuthContext.jsx";
import {errorNotification} from "../../services/Notification.js";
import {useNavigate} from "react-router-dom";
import {useEffect} from "react";

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

const LoginForm = () => {
    const {login} = useAuth();
    const navigate = useNavigate()

    return (
        <>
            <Formik
                validateOnMount={true}
                validationSchema={
                    Yup.object({
                        username: Yup.string()
                            .email("Must be valid email")
                            .required("Email is required"),
                        password: Yup.string()
                            .max(20, "Password cannot be more than 20 characters")
                            .required("Password is required")
                    })
                }
                initialValues={{username:"", password: ""}}
                onSubmit={(values, {setSubmitting}) => {
                    setSubmitting(true);
                    login(values).then(res => {

                        console.log("Successfully logged in");
                        navigate("/dashboard")
                    }).catch(err => {
                        errorNotification(
                            err.code,
                            err.response.data.message
                        )
                    }).finally(() => {
                        setSubmitting(false);
                    })
                }}>

                {({isValid, isSubmitting}) => (
                    <Form>
                        <Stack spacing={15}>
                            <MyTextInput
                                label={"Email"}
                                name={"username"}
                                type={"email"}
                                placeholder={"hello@amcsoftware.com"}
                            />
                            <MyTextInput
                                label={"Password"}
                                name={"password"}
                                type={"password"}
                                placeholder={"Enter a password"}
                            />
                            <Button isDisabled={!isValid||isSubmitting} type="submit">Login</Button>
                        </Stack>
                    </Form>
                )}

            </Formik>
        </>
    )
}

const Login = () => {
    const {student} = useAuth();
    const navigate = useNavigate();
    useEffect(() => {
        if(student) {
            navigate("/dashboard")
        }
    })
    return (
        <Stack minH={'100vh'} direction={{ base: 'column', md: 'row' }}>
            <Flex p={8} flex={1} align={'center'} justify={'center'}>
                <Stack spacing={4} w={'full'} maxW={'md'}>
                    <Heading fontSize={'2xl'} mb={15}>Sign in to your account</Heading>
                    <LoginForm />
                </Stack>
            </Flex>
            <Flex flex={1} p={10}
                  flexDirection={"column"}
                  alignItems={"center"}
                  justifyItems={"center"}
                  bgGradient ={{sm:'linear(to-r, blue.600, purple.600)'}}
            >
                <Text fontSize={"6xl"} color={"white"} fontWeight={"bold"} mb={5}>
                    <Link href={"/"}>Enroll Now</Link>
                </Text>
                <Image
                    alt={'Login Image'}
                    objectFit={'scale-down'}
                    src={enrollment}
                />
            </Flex>
        </Stack>
    );
}
export default  Login