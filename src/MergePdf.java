import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFMergerUtility;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MergePdf {
    public static void main(String[] args) throws IOException {
        System.out.println("Enter full folder path that has pdfs to merge:");
        Scanner scanner = new Scanner(System.in);
        String folderPath = scanner.nextLine();
        //Loading an existing PDF document
        if (folderPath != null && folderPath.length() > 1 && folderPath != " ") {
            File pdfDirectory = new File(folderPath);
            //Instantiating PDFMergerUtility class
            PDFMergerUtility PDFmerger = new PDFMergerUtility();

            //Setting the destination file
            PDFmerger.setDestinationFileName(pdfDirectory + File.separator + "merged.pdf");

            if (pdfDirectory.isDirectory()) {
                File[] filesList = pdfDirectory.listFiles();
                for (File file1 : filesList) {
                    if (file1.isFile() && isPdf(file1)) {
                        PDDocument doc1 = PDDocument.load(file1);
                        PDFmerger.addSource(file1);
                    }
                }
                //Merging the two documents
                try {
                    PDFmerger.mergeDocuments();
                } catch (COSVisitorException e) {
                    e.printStackTrace();
                }
                System.out.println("Documents merged");
            }
        }
    }

    private static boolean isPdf(File file1) {
        boolean status = false;
        String filePath = file1.getName();
        if (filePath.contains("pdf")) {
            status = true;
        }
        return status;
    }

}