# coding=iso-8859-15

"""
    tsv2tex.py

    This script reads a tab separated value (TSV) file and transforms it into a SCI text resource file (TEX).
"""
import os
import sys


def convert_tsv_to_tex(tsv_filepath, tex_filepath, field):
    """
    Converts a tab-separated value (TSV) file to a TEX binary file.

    Args:
        tsv_filepath (str): The path to the input TSV file.
        tex_filepath (str): The path to the output binary file.
        field (int): The field number to extract from the input file.
    """
    try:
        # open the output file for writing in binary mode
        with open(tex_filepath, 'wb') as outfile:
            # write header to output file
            outfile.write(b'\x83\x00')

            # open the input TSV file for reading in text mode
            with open(tsv_filepath, 'r', encoding='utf-8') as infile:
                line_count = 0
                for line in infile:
                    # strip whitespace and split line by tabs
                    fields = line.strip().split('\t')

                    # detect and skip over header
                    if not fields[0].isnumeric():
                        continue

                    # extract the selected field, defaulting to empty
                    text = ""
                    if len(fields) >= field + 1:
                        text = fields[field]

                    # write encoded field to TEX file
                    outfile.write(text.encode('utf-8'))

                    # write null terminator / string separator
                    outfile.write(b'\x00')

                    line_count += 1

        print(f"Successfully converted {line_count} entries from '{tsv_filepath}' field {field} to '{tex_filepath}'.")

    except FileNotFoundError:
        print(f"Error: The file '{tsv_filepath}' was not found.")
        sys.exit(1)
    except Exception as e:
        print(f"An unexpected error occurred: {e}")
        sys.exit(1)

if __name__ == "__main__":
    # validate command line
    if len(sys.argv) != 3:
        print("Usage: python tsv2tex.py <tsv-file> <output-dir>")
        print("   -> tsv-file must be of the format <module>.tsv")
        sys.exit(1)

    tsv_file = sys.argv[1]
    output_dir = sys.argv[2]

    # derive module from input filename
    (module, ext) = os.path.splitext(os.path.basename(tsv_file))
    if not module.isnumeric():
        print(f"Module name '{module}' must be numeric")
        sys.exit(1)

    # derive output file paths
    text_file = f"{output_dir}/{module}.tex"
    selector_file = f"{output_dir}/{int(module) + 1}.tex"
    if not os.path.exists(output_dir):
        os.makedirs(output_dir)

    # convert text (field 4)
    convert_tsv_to_tex(tsv_file, text_file, 4)

    # convert selectors (field 2)
    convert_tsv_to_tex(tsv_file, selector_file, 2)
